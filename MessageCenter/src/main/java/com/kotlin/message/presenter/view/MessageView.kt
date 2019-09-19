package com.kotlin.message.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.message.data.protocol.Message

interface MessageView : BaseView {

    fun onGetMessageResult(result:MutableList<Message>?)
}
