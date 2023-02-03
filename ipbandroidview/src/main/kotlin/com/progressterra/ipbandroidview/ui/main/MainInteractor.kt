package com.progressterra.ipbandroidview.ui.main

import com.progressterra.ipbandroidview.model.store.Category
import com.progressterra.ipbandroidview.model.store.StoreGoods

interface MainInteractor {

    fun onCategory(category: Category)

    fun favoriteSpecific(goods: StoreGoods)

    fun openDetails(goods: StoreGoods)

    fun refresh()

    class Empty : MainInteractor {

        override fun onCategory(category: Category) = Unit

        override fun favoriteSpecific(goods: StoreGoods) = Unit

        override fun openDetails(goods: StoreGoods) = Unit

        override fun refresh() = Unit
    }
}