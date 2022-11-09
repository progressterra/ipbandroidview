package com.progressterra.ipbandroidview.ui.main

import com.progressterra.ipbandroidview.actions.Favorite
import com.progressterra.ipbandroidview.actions.Refresh

interface MainInteractor : Refresh, Favorite {

    fun goodsDetails(id: String)

    class Empty : MainInteractor {

        override fun favorite(goodsId: String, favorite: Boolean) = Unit

        override fun goodsDetails(id: String) = Unit

        override fun refresh() = Unit
    }
}