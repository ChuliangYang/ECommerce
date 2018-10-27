package com.me.cl.popularmovie.mvvm.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

/**
 * Created by CL on 10/8/18.
 */
data class MovieResponse(
        val page: Int,
        val total_results: Int,
        val total_pages: Int,
        val results: List<Movie>
)

@Entity(tableName = "Movies")
data class Movie(
        @PrimaryKey(autoGenerate = true)
        var create_order: Int=0,
        var vote_count: Int?,
        var id: Int?,
        var video: Boolean?,
        var vote_average: Double?,
        var title: String?,
        var popularity: Double?,
        var poster_path: String?,
        var original_language: String?,
        var original_title: String?,
        var genre_ids: List<Int>?,
        var backdrop_path: String?,
        var adult: Boolean?,
        var overview: String?,
        var release_date: String?
)

@Dao
abstract class MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun save(movies: List<Movie>)

    @Query("select * from Movies order by create_order")
    abstract fun getMovies(): LiveData<List<Movie>>

    @Query("DELETE FROM Movies")
    abstract fun deleteAllMovies()

    @Transaction
    open fun replaceAll(movies: List<Movie>) {
        deleteAllMovies()
        save(movies)
    }
}

