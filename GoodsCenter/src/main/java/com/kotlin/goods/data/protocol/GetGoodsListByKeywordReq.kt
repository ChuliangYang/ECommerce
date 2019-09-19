package com.kotlin.goods.data.protocol


data class GetGoodsListByKeywordReq(
        val keyword: String,
        val pageNo: Int
)
