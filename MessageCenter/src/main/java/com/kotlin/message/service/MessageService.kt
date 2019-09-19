package com.kotlin.message.service

import com.kotlin.message.data.protocol.Message
import io.reactivex.Observable

interface MessageService {
    fun getMessageList(): Observable<MutableList<Message>?>

}
