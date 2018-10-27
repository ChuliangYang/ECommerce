package com.me.cl.popularmovie.mvvm.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.me.cl.popularmovie.R
import com.me.cl.popularmovie.mvvm.model.Movie
import com.me.cl.popularmovie.mvvm.api.PICTURE_BASE_URL
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Created by CL on 10/10/18.
 */
class MovieListAdapter(var movieList:MutableList<Movie>):RecyclerView.Adapter<ItemViewHolder>(){
    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemViewHolder{
        return ItemViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_movie,p0,false))
    }

    override fun onBindViewHolder(p0: ItemViewHolder, p1: Int) {
        p0.itemView.apply{
            Glide.with(context).load(PICTURE_BASE_URL +movieList[p1].poster_path).into(iv_movie)
        }
    }
}