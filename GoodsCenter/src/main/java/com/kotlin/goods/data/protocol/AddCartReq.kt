package com.kotlin.goods.data.protocol

data class AddCartReq(
        val goodsId: Int,
        val goodsDesc: String,
        val goodsIcon: String,
        val goodsPrice: Long,
        val goodsCount: Int,
        val goodsSku: String
)
