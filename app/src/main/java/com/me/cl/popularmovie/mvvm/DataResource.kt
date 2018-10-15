package com.me.cl.popularmovie.mvvm

// Encapsulate original data as well as status, represent all data returned by model layer
data class DataResource<out OriginalType>(val loadingStatus:DataStatus, val original:OriginalType?, val extraMessage:String?){
    companion object {
        fun <T> success(originalData:T?, extraMessage:String?=null):DataResource<T>{
            return DataResource(DataStatus.SUCCESS,originalData,extraMessage)
        }
        fun <T> failed(extraMessage:String?, alternativeData:T?=null):DataResource<T>{
            return DataResource(DataStatus.FAILED,alternativeData,extraMessage)
        }
        fun <T> loading(progress:Int?=null, originalData:T?=null):DataResource<T>{
            return DataResource(DataStatus.LOADING,originalData,progress?.toString())
        }
        fun <T> Absent():DataResource<T>{
            return DataResource(DataStatus.NULL,null,null)
        }
    }

    fun isSuccess():Boolean{
        return loadingStatus == DataStatus.SUCCESS
    }
}

enum class DataStatus{
    SUCCESS,
    FAILED,
    LOADING,
    NULL
}