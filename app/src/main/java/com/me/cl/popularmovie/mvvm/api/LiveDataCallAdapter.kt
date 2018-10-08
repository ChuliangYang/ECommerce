package com.me.cl.popularmovie.mvvm.api

import android.arch.lifecycle.LiveData
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A Retrofit adapter that converts the Call into a LiveData of NetworkResponse.
 * @param <R>
</R> */
class LiveDataCallAdapter<R>(private val responseType: Type) :
        CallAdapter<R, LiveData<NetworkResponse<R>>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<R>): LiveData<NetworkResponse<R>> {
        return object : LiveData<NetworkResponse<R>>() {
            private var started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(NetworkResponse.create(response))
                        }

                        override fun onFailure(call: Call<R>, throwable: Throwable) {
                            postValue(NetworkResponse.create(throwable))
                        }
                    })
                }
            }
        }
    }
}


class LiveDataCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
            returnType: Type,
            annotations: Array<Annotation>,
            retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (CallAdapter.Factory.getRawType(returnType) != LiveData::class.java) {
            return null
        }
        val observableType = CallAdapter.Factory.getParameterUpperBound(0, returnType as ParameterizedType)
        val rawObservableType = CallAdapter.Factory.getRawType(observableType)
        if (rawObservableType != NetworkResponse::class.java) {
            throw IllegalArgumentException("type must be a NetworkResponse")
        }
        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException("NetworkResponse must be parameterized")
        }
        val bodyType = CallAdapter.Factory.getParameterUpperBound(0, observableType)
        return LiveDataCallAdapter<Any>(bodyType)
    }
}