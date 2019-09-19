package com.kotlin.goods.data.protocol


data class SubmitCartReq(val goodsList: List<CartGoods>,val totalPrice: Long)
