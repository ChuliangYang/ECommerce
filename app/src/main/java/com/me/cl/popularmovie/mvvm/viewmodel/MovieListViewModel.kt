package com.me.cl.popularmovie.mvvm.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import com.me.cl.popularmovie.mvvm.DataResource
import com.me.cl.popularmovie.mvvm.Movie
import com.me.cl.popularmovie.mvvm.repos.MovieListRepository
import javax.inject.Inject

class MovieListViewModel @Inject constructor(val movieListRepository: MovieListRepository) : BaseViewModel(movieListRepository) {

    val mediatorMoviesLiveData=MediatorLiveData<MovieListModel>()

    fun getMovieList(cate:String,page:Int,onFinishListener:(()->Unit)?=null):MediatorLiveData<MovieListModel>{
        val repoList=movieListRepository.getMovieList(cate, page)
        mediatorMoviesLiveData.addSource(repoList) {dataResource->
            mediatorMoviesLiveData.removeSource(repoList)
            (dataResource?.original?: mutableListOf()).let { movies ->
                val persist=movieListRepository.persistMovieList(movies,page==1)
                mediatorMoviesLiveData.addSource(persist) {
                    mediatorMoviesLiveData.removeSource(persist)
                    mediatorMoviesLiveData.value=MovieListModel(dataResource,cate,page)
                    onFinishListener?.invoke()
                }
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
                mediatorMoviesLiveData.value=MovieListModel(DataResource.success(it),"",1)
            })

        }
    }

}

data class MovieListModel(
        val movieList:DataResource<List<Movie>>?,
        val mCate:String,
        val mPage:Int
)
