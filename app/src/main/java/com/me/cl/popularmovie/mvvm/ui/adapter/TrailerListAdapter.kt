package com.me.cl.popularmovie.mvvm.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.me.cl.popularmovie.R
import com.me.cl.popularmovie.mvvm.Trailer
import kotlinx.android.synthetic.main.item_trailer.view.*

/**
 * Created by CL on 10/10/18.
 */
class TrailerListAdapter(val trailerList:List<Trailer>):RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_trailer,p0,false))
    }

    override fun getItemCount(): Int {
        return trailerList.size
    }

    override fun onBindViewHolder(p0: ItemViewHolder, p1: Int) {
        p0.itemView.apply {
            tv_trailer.text="Trailer${p1+1}"
        }
    }
}