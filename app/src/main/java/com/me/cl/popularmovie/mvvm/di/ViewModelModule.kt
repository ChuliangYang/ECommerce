package com.me.cl.template.presentation.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.me.cl.popularmovie.mvvm.model.MovieDao
import com.me.cl.popularmovie.mvvm.di.ViewModelKey
import com.me.cl.popularmovie.mvvm.local.AppDatabase
import com.me.cl.popularmovie.mvvm.viewmodel.MovieListViewModel
import com.me.cl.popularmovie.mvvm.viewmodel.MyViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Suppress("unused")
@Module
abstract class ViewModelModule{
    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    abstract fun bindCityListViewModel(movieListViewModel:MovieListViewModel): ViewModel

    @Singleton
    @Binds
    abstract fun bindViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory


    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideMovieDao(dataBase: AppDatabase): MovieDao {
            return dataBase.MovieDao()
        }
    }
}