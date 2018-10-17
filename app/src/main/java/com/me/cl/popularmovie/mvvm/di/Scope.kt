package com.me.cl.popularmovie.mvvm.di

import javax.inject.Scope


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerFragment