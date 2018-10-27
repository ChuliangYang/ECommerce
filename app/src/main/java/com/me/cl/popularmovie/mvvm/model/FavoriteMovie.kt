package com.me.cl.popularmovie.mvvm.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

/**
 * Created by CL on 10/9/18.
 */
@Entity(tableName = "Favorite_Movies")
data class FavoriteMovie(
        val vote_count: Int,
        @PrimaryKey
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

@Dao
interface FavoriteMovieDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAsFavorite(favoriteMovie: FavoriteMovie)

    @Delete
    fun removeFavorite(favoriteMovie: FavoriteMovie)

    @Query("select * from Favorite_Movies")
    fun getFavoriteMovies():LiveData<List<FavoriteMovie>>
}