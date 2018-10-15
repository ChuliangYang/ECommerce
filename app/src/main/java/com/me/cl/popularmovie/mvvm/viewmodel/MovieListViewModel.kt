package com.me.cl.popularmovie.mvvm.viewmodel

import android.arch.lifecycle.LiveData
import com.me.cl.popularmovie.mvvm.DataResource
import com.me.cl.popularmovie.mvvm.Movie
import com.me.cl.popularmovie.mvvm.repos.MovieListRepository

class MovieListViewModel(val movieListRepository: MovieListRepository) : BaseViewModel(movieListRepository) {
    fun getMovieList(cate:String,page:Int): LiveData<DataResource<List<Movie>>>{
        return reuseWhenAlive {
            movieListRepository.getMovieList(cate, page)
        }
    }
}
