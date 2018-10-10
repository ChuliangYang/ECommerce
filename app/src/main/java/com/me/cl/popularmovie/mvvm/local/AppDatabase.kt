package com.me.cl.popularmovie.mvvm.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.me.cl.popularmovie.mvvm.Converter

/**
 * Created by CL on 10/9/18.
 */
@Database(entities = arrayOf(FavoriteMovie::class),version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase:RoomDatabase() {
    abstract fun FavoriteMovieDao():FavoriteMovieDao
}