package com.me.cl.popularmovie.mvvm.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by CL on 10/10/18.
 */
data class Trailer(
        @Expose
        @SerializedName("id")
        val id: String,
        @Expose
        @SerializedName("iso_639_1")
        val iso_639_1: String,
        @Expose
        @SerializedName("iso_3166_1")
        val iso_3166_1: String,
        @Expose
        @SerializedName("key")
        val key: String,
        @Expose
        @SerializedName("name")
        val name: String,
        @Expose
        @SerializedName("site")
        val site: String,
        @Expose
        @SerializedName("size")
        val size: Int,
        @Expose
        @SerializedName("type")
        val type: String)