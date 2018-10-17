package com.me.cl.popularmovie.mvvm

/**
 * Created by CL on 10/8/18.
 */
data class MovieResponse(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<Movie>
)

data class Movie(
    val vote_count: Int,
    val id: Int,
    val video: Boolean,
    val vote_average: Double,
    val title: String,
    val popularity: Double,
    val poster_path: String,
    val original_language: String,
    val original_title: String,
    val genre_ids: List<Int>,
    val backdrop_path: String,
    val adult: Boolean,
    val overview: String,
    val release_date: String
)