package com.me.cl.popularmovie.mvvm.di

import android.app.Application
import android.arch.persistence.room.Room
import com.me.cl.popularmovie.mvvm.API_KEY
import com.me.cl.popularmovie.mvvm.MovieApplication
import com.me.cl.popularmovie.mvvm.SERVER_HOST
import com.me.cl.popularmovie.mvvm.api.LiveDataCallAdapterFactory
import com.me.cl.popularmovie.mvvm.api.MovieService
import com.me.cl.popularmovie.mvvm.local.AppDatabase
import com.me.cl.template.presentation.di.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(SERVER_HOST).client(OkHttpClient.Builder().apply {
//            addNetworkInterceptor(HttpLoggingInterceptor().apply {
//                level = HttpLoggingInterceptor.Level.BASIC
//            })
            addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            )
            addNetworkInterceptor {chain->
                val request=chain.request()
                request.url().newBuilder().addQueryParameter("api_key", API_KEY).build().let {
                    request.newBuilder().url(it).build().let {
                        chain.proceed(it)
                    }
                }
            }
        }.build()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(LiveDataCallAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun provideGistService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @Singleton
    @Provides
    fun provideRoomDataBase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "popular_movies").build()
    }
}

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class
    , MainActivitySubComponentModule::class
])
interface AppComponent{
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application):Builder
        fun build():AppComponent
    }

    fun inject(application: MovieApplication)
}