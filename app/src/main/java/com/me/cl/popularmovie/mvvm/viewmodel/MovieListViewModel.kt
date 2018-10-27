package com.me.cl.popularmovie.mvvm.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Transformations
import com.me.cl.popularmovie.mvvm.model.DataResource
import com.me.cl.popularmovie.mvvm.model.Movie
import com.me.cl.popularmovie.mvvm.repos.MovieListRepository
import com.me.cl.popularmovie.mvvm.utils.SingleLiveEvent
import javax.inject.Inject

class MovieListViewModel @Inject constructor(val movieListRepository: MovieListRepository) : BaseViewModel(movieListRepository) {

    val mediatorMoviesLiveData= SingleLiveEvent<MovieListModel>()

    fun getMovieList(cate:String,page:Int,onFinishListener:(()->Unit)?=null):MediatorLiveData<MovieListModel>{
        var dataResource: DataResource<List<Movie>>?=null
        Transformations.switchMap(movieListRepository.getMovieList(cate, page)) {it->
            dataResource=it
            movieListRepository.persistMovieList(it.original?:mutableListOf(),page==1)
        }.run{
            mediatorMoviesLiveData.addSource(this) {
                mediatorMoviesLiveData.removeSource(this)
                mediatorMoviesLiveData.value=MovieListModel(dataResource,cate,page)
                onFinishListener?.invoke()
            }
        }

        return mediatorMoviesLiveData
    }

    fun subscribePageListEvent():LiveData<MovieListModel>{
        return mediatorMoviesLiveData
    }

    fun restoreMovieList(){
        movieListRepository.restoreMovieList().run{
            mediatorMoviesLiveData.addSource(this,{
                mediatorMoviesLiveData.removeSource(this)
                mediatorMoviesLiveData.value=MovieListModel(DataResource.success(it),"",0)
            })

        }
    }

}

data class MovieListModel(
        val movieList: DataResource<List<Movie>>?,
        val mCate:String,
        val mPage:Int
)
