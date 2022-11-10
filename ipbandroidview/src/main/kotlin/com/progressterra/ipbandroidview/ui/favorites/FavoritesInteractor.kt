package com.progressterra.ipbandroidview.ui.favorites

import com.progressterra.ipbandroidview.actions.Favorite
import com.progressterra.ipbandroidview.actions.Refresh
import com.progressterra.ipbandroidview.model.Goods

interface FavoritesInteractor : Refresh, Favorite {

    fun goodsDetails(goods: Goods)

    class Empty : FavoritesInteractor {

        override fun favorite(goodsId: String, favorite: Boolean) = Unit

        override fun refresh() = Unit

        override fun goodsDetails(goods: Goods) = Unit
    }
}