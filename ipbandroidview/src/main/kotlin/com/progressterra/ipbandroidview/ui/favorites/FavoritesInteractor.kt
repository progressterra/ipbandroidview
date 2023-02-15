package com.progressterra.ipbandroidview.ui.favorites

import com.progressterra.ipbandroidview.composable.component.StoreCardComponentState
import com.progressterra.ipbandroidview.composable.component.StoreCardInteractor

interface FavoritesInteractor : StoreCardInteractor {

    fun refresh()

    class Empty : FavoritesInteractor {

        override fun onClick(storeCard: StoreCardComponentState) = Unit

        override fun favorite(storeCard: StoreCardComponentState) = Unit

        override fun refresh() = Unit
    }
}