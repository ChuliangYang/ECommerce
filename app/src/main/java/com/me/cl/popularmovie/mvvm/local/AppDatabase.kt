package com.me.cl.popularmovie.mvvm.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.me.cl.popularmovie.mvvm.model.FavoriteMovie
import com.me.cl.popularmovie.mvvm.model.FavoriteMovieDao
import com.me.cl.popularmovie.mvvm.model.Movie
import com.me.cl.popularmovie.mvvm.model.MovieDao

/**
 * Created by CL on 10/9/18.
 */
@Database(entities = arrayOf(FavoriteMovie::class, Movie::class),version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase:RoomDatabase() {
    abstract fun FavoriteMovieDao(): FavoriteMovieDao
    abstract fun MovieDao(): MovieDao
}