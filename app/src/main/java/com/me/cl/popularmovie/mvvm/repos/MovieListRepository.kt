package com.me.cl.popularmovie.mvvm.repos

import android.arch.lifecycle.LiveData
import com.me.cl.popularmovie.mvvm.DataResource
import com.me.cl.popularmovie.mvvm.Movie
import com.me.cl.popularmovie.mvvm.ReactUtil.remoteToLiveDataResource
import com.me.cl.popularmovie.mvvm.api.MovieService

/**
 * Created by CL on 10/15/18.
 */
class MovieListRepository constructor(val movieService: MovieService):BaseRepository() {
    fun getMovieList(cate:String,page:Int):LiveData<DataResource<List<Movie>>>{
        return remoteToLiveDataResource(movieService.getMovies(cate,page))
    }
}