package com.progressterra.ipbandroidview.ui.goods

import com.progressterra.ipbandroidview.composable.component.GoodsBarComponentEvent
import com.progressterra.ipbandroidview.composable.component.StoreCardComponentState
import com.progressterra.ipbandroidview.composable.component.StoreCardInteractor
import com.progressterra.ipbandroidview.composable.component.TextFieldEvent
import com.progressterra.ipbandroidview.composable.component.UseGoodsBarComponent

interface GoodsInteractor : StoreCardInteractor, UseGoodsBarComponent {

    fun refresh()

    class Empty : GoodsInteractor {

        override fun handleEvent(id: String, event: GoodsBarComponentEvent) = Unit

        override fun handleEvent(id: String, event: TextFieldEvent) = Unit

        override fun refresh() = Unit

        override fun onClick(storeCard: StoreCardComponentState) = Unit

        override fun favorite(storeCard: StoreCardComponentState) = Unit
    }
}