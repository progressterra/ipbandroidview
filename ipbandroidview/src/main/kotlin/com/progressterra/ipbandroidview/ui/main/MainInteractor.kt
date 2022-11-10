package com.progressterra.ipbandroidview.ui.main

import com.progressterra.ipbandroidview.actions.Favorite
import com.progressterra.ipbandroidview.actions.Refresh
import com.progressterra.ipbandroidview.model.Goods

interface MainInteractor : Refresh, Favorite {

    fun goodsDetails(goods: Goods)

    class Empty : MainInteractor {

        override fun favorite(goodsId: String, favorite: Boolean) = Unit

        override fun goodsDetails(goods: Goods) = Unit

        override fun refresh() = Unit
    }
}