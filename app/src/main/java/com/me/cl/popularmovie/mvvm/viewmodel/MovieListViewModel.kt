package com.me.cl.popularmovie.mvvm.viewmodel

import android.arch.lifecycle.LiveData
import com.me.cl.popularmovie.mvvm.DataResource
import com.me.cl.popularmovie.mvvm.Movie
import com.me.cl.popularmovie.mvvm.POPULAR
import com.me.cl.popularmovie.mvvm.TOP_RATE
import com.me.cl.popularmovie.mvvm.repos.MovieListRepository
import javax.inject.Inject

class MovieListViewModel @Inject constructor(val movieListRepository: MovieListRepository) : BaseViewModel(movieListRepository) {

    fun getPopularMoiveList(page:Int):LiveData<DataResource<List<Movie>>>{
        return getMovieList(POPULAR,page)
    }

    fun getTopRatedMoiveList(page:Int):LiveData<DataResource<List<Movie>>>{
        return reuseWhenAlive{getMovieList(TOP_RATE,page)}
    }

    private fun getMovieList(cate:String,page:Int): LiveData<DataResource<List<Movie>>>{
        return movieListRepository.getMovieList(cate, page)

    }

}
