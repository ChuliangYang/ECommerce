package com.kotlin.message.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.ext.loadUrl


import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.message.R
import com.kotlin.message.data.protocol.Message
import kotlinx.android.synthetic.main.layout_message_item.view.*

class MessageAdapter(context: Context) : BaseRecyclerViewAdapter<Message, MessageAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_message_item,
                        parent,
                        false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        holder.itemView.mMsgIconIv.loadUrl(model.msgIcon)
        holder.itemView.mMsgTitleTv.text = model.msgTitle
        holder.itemView.mMsgContentTv.text = model.msgContent
        holder.itemView.mMsgTimeTv.text = model.msgTime
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
