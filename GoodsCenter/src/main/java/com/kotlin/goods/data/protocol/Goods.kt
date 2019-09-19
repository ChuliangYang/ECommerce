package com.kotlin.goods.data.protocol


data class Goods(
        val id: Int,
        val categoryId: Int,
        val goodsDesc: String,
        val goodsDefaultIcon: String,
        val goodsDefaultPrice: Long,
        val goodsDetailOne: String,
        val goodsDetailTwo: String,
        val goodsSalesCount: Int,
        val goodsStockCount: Int,
        val goodsCode: String,
        val goodsDefaultSku: String,
        val goodsBanner: String,
        val goodsSku:List<GoodsSku>,
        val maxPage:Int
        )
