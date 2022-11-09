package com.progressterra.ipbandroidview.ui.goods

import com.progressterra.ipbandroidview.actions.Back
import com.progressterra.ipbandroidview.actions.Favorite
import com.progressterra.ipbandroidview.actions.Refresh

interface GoodsInteractor : Refresh, Back, Favorite {

    fun goodsDetails(goodsId: String)

    class Empty : GoodsInteractor {

        override fun back() = Unit

        override fun favorite(goodsId: String, favorite: Boolean) = Unit

        override fun refresh() = Unit

        override fun goodsDetails(goodsId: String) = Unit
    }
}