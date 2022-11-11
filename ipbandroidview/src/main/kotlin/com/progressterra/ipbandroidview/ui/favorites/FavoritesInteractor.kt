package com.progressterra.ipbandroidview.ui.favorites

import com.progressterra.ipbandroidview.actions.FavoriteSpecific
import com.progressterra.ipbandroidview.actions.Refresh
import com.progressterra.ipbandroidview.model.Goods

interface FavoritesInteractor : Refresh, FavoriteSpecific<Goods> {

    fun goodsDetails(goods: Goods)

    class Empty : FavoritesInteractor {

        override fun favoriteSpecific(item: Goods) = Unit

        override fun refresh() = Unit

        override fun goodsDetails(goods: Goods) = Unit
    }
}