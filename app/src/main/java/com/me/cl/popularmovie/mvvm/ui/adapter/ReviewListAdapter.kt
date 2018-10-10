package com.me.cl.popularmovie.mvvm.ui.adapter

import android.databinding.DataBindingUtil
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.me.cl.popularmovie.R
import com.me.cl.popularmovie.databinding.ItemReviewBinding
import com.me.cl.popularmovie.mvvm.Review

/**
 * Created by CL on 10/10/18.
 */
class ReviewListAdapter:ListAdapter<Review,DataBoundViewHolder<ItemReviewBinding>>(object: DiffUtil.ItemCallback<Review?>() {
    override fun areItemsTheSame(p0: Review, p1: Review): Boolean {
       return p0.id==p1.id
    }

    override fun areContentsTheSame(p0: Review, p1: Review): Boolean {
        return p0.content==p1.content
    }
}){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): DataBoundViewHolder<ItemReviewBinding> {
        return DataBoundViewHolder(DataBindingUtil.inflate(LayoutInflater.from(p0.context), R.layout.item_review,p0,false))
    }

    override fun onBindViewHolder(p0: DataBoundViewHolder<ItemReviewBinding>, p1: Int) {
        p0.binding.apply {
           review=getItem(p1)
        }
    }
}