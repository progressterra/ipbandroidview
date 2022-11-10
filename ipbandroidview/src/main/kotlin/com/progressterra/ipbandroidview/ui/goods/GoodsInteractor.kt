package com.progressterra.ipbandroidview.ui.goods

import com.progressterra.ipbandroidview.actions.Back
import com.progressterra.ipbandroidview.actions.Favorite
import com.progressterra.ipbandroidview.actions.Refresh
import com.progressterra.ipbandroidview.model.Goods

interface GoodsInteractor : Refresh, Back, Favorite {

    fun goodsDetails(goods: Goods)

    class Empty : GoodsInteractor {

        override fun back() = Unit

        override fun favorite(goodsId: String, favorite: Boolean) = Unit

        override fun refresh() = Unit

        override fun goodsDetails(goods: Goods) = Unit
    }
}