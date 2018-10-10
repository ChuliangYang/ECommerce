package com.me.cl.popularmovie.mvvm

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken



/**
 * Created by CL on 10/10/18.
 */

class Converter{
    val gson=Gson()
    @TypeConverter
    fun ListIntergerToString(ids:List<Int>):String{
        return gson.toJson(ids)
    }

    @TypeConverter
    fun StringToListInterger(ids:String):List<Int>{
        val listType = object : TypeToken<List<Int>>() {
        }.type
        return gson.fromJson(ids, listType)
    }
}