package com.me.cl.popularmovie.mvvm.di

import android.app.Application
import com.me.cl.popularmovie.mvvm.MovieApplication
import com.me.cl.popularmovie.mvvm.SERVER_HOST
import com.me.cl.popularmovie.mvvm.api.LiveDataCallAdapterFactory
import com.me.cl.template.MyApplication
import com.me.cl.template.framework.api.LiveDataCallAdapterFactory
import com.me.cl.template.presentation.api.ApiService
import com.me.cl.template.presentation.api.SERVER_HOST
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
//(includes = [ViewModelModule::class])
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
            })
        }.build()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(LiveDataCallAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun provideGistService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

//    @Singleton
//    @Provides
//    fun provideRoomDataBase(application: Application): AppDatabase {
//        return Room.databaseBuilder(application, AppDatabase::class.java, "material-tab").build()
//    }
}

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class
//    , MainActivitySubComponentModule::class
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