package com.kotlin.goods.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.goods.data.protocol.Category
interface CategoryView : BaseView {

    fun onGetCategoryResult(result: MutableList<Category>?)
}
