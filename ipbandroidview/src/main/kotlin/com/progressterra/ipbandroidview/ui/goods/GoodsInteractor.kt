package com.progressterra.ipbandroidview.ui.goods

import com.progressterra.ipbandroidview.model.store.StoreGoods

interface GoodsInteractor {

    fun search()

    fun editKeyword(keyword: String)

    fun onBack()

    fun favoriteSpecific(goods: StoreGoods)

    fun refresh()

    fun openDetails(goods: StoreGoods)

    fun filters()

    fun clear()

    class Empty : GoodsInteractor {

        override fun search() = Unit

        override fun editKeyword(keyword: String) = Unit

        override fun onBack() = Unit

        override fun favoriteSpecific(goods: StoreGoods) = Unit

        override fun refresh() = Unit

        override fun openDetails(goods: StoreGoods) = Unit

        override fun filters() = Unit

        override fun clear() = Unit
    }
}