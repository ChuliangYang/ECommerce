package com.me.cl.popularmovie.mvvm.viewmodel

import android.arch.lifecycle.ViewModel
import com.me.cl.popularmovie.mvvm.repos.BaseRepository
import com.me.cl.popularmovie.mvvm.repos.ClearableRepository

open class BaseViewModel(val repo: BaseRepository?=null): ViewModel(){
    var activityScopedCache:MutableMap<String?,Any?>? = mutableMapOf()

    override fun onCleared() {
        super.onCleared()
        repo?.apply{
            if(this is ClearableRepository){
                clear()
            }
        }
        activityScopedCache?.clear()
        activityScopedCache=null
    }
}

inline fun getMethodName():String{
    return Throwable().stackTrace[0].methodName
}

inline fun <T> BaseViewModel.reuseWhenAlive(additionKey:String?=null, dataProducer:()->T):T{
    val key="${getMethodName()}${additionKey?.let{
        ":$it"
    }}"
    return if(activityScopedCache?.get(key) !=null){
        activityScopedCache!![key] as T
    }else{
        dataProducer().apply {
            activityScopedCache?.set(key, this)
        }
    }
}