package com.progressterra.ipbandroidview.ui.favorites

import com.progressterra.ipbandroidview.model.store.StoreGoods

interface FavoritesInteractor {

    fun favoriteSpecific(goods: StoreGoods)

    fun refresh()

    fun openDetails(goods: StoreGoods)

    class Empty : FavoritesInteractor {

        override fun favoriteSpecific(goods: StoreGoods) = Unit

        override fun refresh() = Unit

        override fun openDetails(goods: StoreGoods) = Unit
    }
}