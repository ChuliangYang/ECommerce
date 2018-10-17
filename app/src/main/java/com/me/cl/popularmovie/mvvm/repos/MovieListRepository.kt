package com.me.cl.popularmovie.mvvm.repos

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations.map
import com.me.cl.popularmovie.mvvm.DataResource
import com.me.cl.popularmovie.mvvm.Movie
import com.me.cl.popularmovie.mvvm.ReactUtil.remoteToLiveDataResource
import com.me.cl.popularmovie.mvvm.api.MovieService
import javax.inject.Inject

/**
 * Created by CL on 10/15/18.
 */
class MovieListRepository @Inject constructor(val movieService: MovieService):BaseRepository() {
    fun getMovieList(cate:String,page:Int):LiveData<DataResource<List<Movie>>>{
        return map(remoteToLiveDataResource(movieService.getMovies(cate,page))) {
            it.transformContent {
                it?.results?: listOf()
            }
        }
    }
}