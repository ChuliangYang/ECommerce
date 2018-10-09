package com.me.cl.popularmovie.mvvm.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by CL on 10/9/18.
 */
@Database(entities = arrayOf(FavoriteMovie::class),version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun FavoriteMovieDao():FavoriteMovieDao
}