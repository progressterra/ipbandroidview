package com.progressterra.ipbandroidview.ui.goods

import com.progressterra.ipbandroidview.composable.component.StoreCardComponentState
import com.progressterra.ipbandroidview.composable.component.StoreCardInteractor

interface GoodsInteractor : StoreCardInteractor {

    fun search()

    fun editKeyword(keyword: String)

    fun onBack()

    fun refresh()

    fun filters()

    fun clear()

    class Empty : GoodsInteractor {

        override fun search() = Unit

        override fun editKeyword(keyword: String) = Unit

        override fun onBack() = Unit

        override fun refresh() = Unit

        override fun filters() = Unit

        override fun clear() = Unit

        override fun onClick(storeCard: StoreCardComponentState) = Unit

        override fun favorite(storeCard: StoreCardComponentState) = Unit
    }
}