package com.me.cl.template.presentation.di

import com.me.cl.popularmovie.mvvm.di.PerFragment
import com.me.cl.popularmovie.mvvm.ui.MovieListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityFragmentSubComponentModule{
    @PerFragment
    @ContributesAndroidInjector(modules = [MainActivityFragmentModule::class])
    abstract fun cityListFragmentInjector(): MovieListFragment
}

@Module
abstract class MainActivityFragmentModule{

}