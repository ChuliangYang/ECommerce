package com.me.cl.popularmovie.mvvm.repos

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.SingleGeneratedAdapterObserver
import android.arch.lifecycle.Transformations.map
import com.me.cl.popularmovie.mvvm.DataResource
import com.me.cl.popularmovie.mvvm.Movie
import com.me.cl.popularmovie.mvvm.MovieDao
import com.me.cl.popularmovie.mvvm.ReactUtil.remoteToLiveDataResource
import com.me.cl.popularmovie.mvvm.api.MovieService
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by CL on 10/15/18.
 */
class MovieListRepository @Inject constructor(val movieService: MovieService, val movieDao:MovieDao):BaseRepository() {
    fun getMovieList(cate:String,page:Int):LiveData<DataResource<List<Movie>>>{
        return map(remoteToLiveDataResource(movieService.getMovies(cate,page))) {
            it.transformContent {
                it?.results?: listOf()
            }
        }
    }

    fun persistMovieList(movies:List<Movie>,replace: Boolean):LiveData<Boolean>{
        val tempLiveData=MutableLiveData<Boolean>()
        Completable.create{
            if(replace){
                movieDao.replaceAll(movies)
            }else{
                movieDao.save(movies)
            }
            it.onComplete()
        }.subscribeOn(Schedulers.io()).subscribe {
            tempLiveData.postValue(true)
        }
       return  tempLiveData
    }

    fun restoreMovieList():LiveData<List<Movie>>{
        return movieDao.getMovies()
    }
}