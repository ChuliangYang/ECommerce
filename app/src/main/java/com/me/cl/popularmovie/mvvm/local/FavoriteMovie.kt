package com.me.cl.popularmovie.mvvm.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.me.cl.popularmovie.mvvm.Movie

/**
 * Created by CL on 10/9/18.
 */
@Entity(tableName = "Favorite_Movies")
data class FavoriteMovie(
        @Embedded
        val movie:Movie
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