package com.me.cl.template.presentation.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.me.cl.popularmovie.mvvm.di.ViewModelKey
import com.me.cl.popularmovie.mvvm.viewmodel.MovieListViewModel
import com.me.cl.popularmovie.mvvm.viewmodel.MyViewModelFactory
import dagger.Binds
import dagger.Module
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
//        @JvmStatic
//        @Provides
//        fun provideCityDao(dataBase:AppDatabase): CityDao{
//            return dataBase.cityDao()
//        }
    }
}