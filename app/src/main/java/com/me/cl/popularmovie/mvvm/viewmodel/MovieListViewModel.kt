package com.me.cl.popularmovie.mvvm.viewmodel

import android.arch.lifecycle.MediatorLiveData
import com.me.cl.popularmovie.mvvm.DataResource
import com.me.cl.popularmovie.mvvm.Movie
import com.me.cl.popularmovie.mvvm.repos.MovieListRepository
import javax.inject.Inject

class MovieListViewModel @Inject constructor(val movieListRepository: MovieListRepository) : BaseViewModel(movieListRepository) {

    val mediatorMoviesLiveData=MediatorLiveData<MovieListModel>()

    fun getMovieList(cate:String,page:Int,onFinishListener:(()->Unit)?=null):MediatorLiveData<MovieListModel>{
        val repoList=movieListRepository.getMovieList(cate, page)
        mediatorMoviesLiveData.addSource(repoList) {
            mediatorMoviesLiveData.removeSource(repoList)
            mediatorMoviesLiveData.value=MovieListModel(it,cate,page)
            onFinishListener?.invoke()
        }
        return mediatorMoviesLiveData
    }
}

data class MovieListModel(
        val movieList:DataResource<List<Movie>>?,
        val mCate:String,
        val mPage:Int
)
