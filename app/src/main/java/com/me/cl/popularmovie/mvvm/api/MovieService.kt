package com.me.cl.popularmovie.mvvm.api

import android.arch.lifecycle.LiveData
import com.me.cl.popularmovie.mvvm.Movie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by CL on 10/8/18.
 */
interface MovieService {
    @GET("movie/{cate}")
    fun getMovies(@Path("cate") cate:String,@Query("page") page:Int):LiveData<NetworkResponse<List<Movie>>>
}