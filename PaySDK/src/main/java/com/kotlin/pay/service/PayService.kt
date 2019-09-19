package com.kotlin.pay.service

import io.reactivex.Observable


interface PayService {

    fun getPaySign(orderId: Int, totalPrice: Long): Observable<String>

    fun payOrder(orderId: Int): Observable<Boolean>
}
