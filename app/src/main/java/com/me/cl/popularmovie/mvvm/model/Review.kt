package com.me.cl.popularmovie.mvvm.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by CL on 10/10/18.
 */
data class Review(
        @Expose
        @SerializedName("author")
        val author: String,
        @Expose
        @SerializedName("content")
        val content: String,
        @Expose
        @SerializedName("id")
        val id: String,
        @Expose
        @SerializedName("url")
        val url: String)