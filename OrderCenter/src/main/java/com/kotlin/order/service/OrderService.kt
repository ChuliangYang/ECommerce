package com.kotlin.order.service

import com.kotlin.order.data.protocol.Order
import io.reactivex.Observable

interface OrderService {

    fun getOrderById(orderId: Int): Observable<Order>

    fun submitOrder(order: Order): Observable<Boolean>

    fun getOrderList(orderStatus: Int): Observable<MutableList<Order>?>

    fun cancelOrder(orderId: Int): Observable<Boolean>

    fun confirmOrder(orderId: Int): Observable<Boolean>
}
