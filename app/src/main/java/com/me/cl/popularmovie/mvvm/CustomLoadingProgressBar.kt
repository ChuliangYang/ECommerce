package com.me.cl.popularmovie.mvvm

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.me.cl.popularmovie.R

/**
 * Created by CL on 10/15/18.
 */
class CustomLoadingListItemCreator : com.paginate.recycler.LoadingListItemCreator {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.custom_loading_list_item, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val vh = holder as VH
//        vh.tvLoading.text = String.format("Total items loaded: %d.\nLoading more...", adapter.getItemCount())
//
//        // This is how you can make full span if you are using StaggeredGridLayoutManager
//        if (recyclerView.getLayoutManager() is StaggeredGridLayoutManager) {
//            val params = vh.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
//            params.isFullSpan = true
//        }
    }
}

internal class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvLoading: TextView

    init {
        tvLoading = itemView.findViewById(R.id.tv_loading_text) as TextView
    }
}
