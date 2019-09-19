package com.kotlin.order.data.api

import retrofit2.http.Body
import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.order.data.protocol.*
import com.kotlin.order.data.protocol.Order
import io.reactivex.Observable
import retrofit2.http.POST


interface OrderApi {

    @POST("order/cancel")
    fun cancelOrder(@Body req: CancelOrderReq): Observable<BaseResp<String>>

    @POST("order/confirm")
    fun confirmOrder(@Body req: ConfirmOrderReq): Observable<BaseResp<String>>

    @POST("order/getOrderById")
    fun getOrderById(@Body req: GetOrderByIdReq): Observable<BaseResp<Order>>

    @POST("order/getOrderList")
    fun getOrderList(@Body req: GetOrderListReq): Observable<BaseResp<MutableList<Order>?>>

    @POST("order/submitOrder")
    fun submitOrder(@Body req: SubmitOrderReq): Observable<BaseResp<String>>

}
