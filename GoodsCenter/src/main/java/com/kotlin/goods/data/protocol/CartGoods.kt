package com.kotlin.goods.data.protocol

data class CartGoods(
        val id: Int,
        val goodsId:Int,
        val goodsDesc: String,
        val goodsIcon: String,
        val goodsPrice: Long,
        var goodsCount: Int,
        val goodsSku:String,
        var isSelected:Boolean
        )

