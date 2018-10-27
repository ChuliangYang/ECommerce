package com.me.cl.popularmovie.mvvm.repos

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations.map
import com.me.cl.popularmovie.mvvm.api.MovieService
import com.me.cl.popularmovie.mvvm.model.DataResource
import com.me.cl.popularmovie.mvvm.model.Movie
import com.me.cl.popularmovie.mvvm.model.MovieDao
import com.me.cl.popularmovie.mvvm.utils.ReactUtil.remoteToLiveDataResource
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by CL on 10/15/18.
 */
class MovieListRepository @Inject constructor(val movieService: MovieService, val movieDao: MovieDao):BaseRepository() {
    fun getMovieList(cate:String,page:Int):LiveData<DataResource<List<Movie>>>{
        return map(remoteToLiveDataResource(movieService.getMovies(cate,page))) {
            it.transformContent {
                it?.results?: listOf()
            }
        }
    }

    fun persistMovieList(movies:List<Movie>, replace: Boolean):LiveData<List<Movie>>{
        val tempLiveData=MutableLiveData<List<Movie>>()
        Completable.create{
            if(replace){
                movieDao.replaceAll(movies)
            }else{
                movieDao.save(movies)
            }
            tempLiveData.postValue(movies)
            it.onComplete()
        }.subscribeOn(Schedulers.io()).subscribe()
       return  tempLiveData
    }

    fun restoreMovieList():LiveData<List<Movie>>{
        return movieDao.getMovies()
    }
}