package com.progressterra.ipbandroidview.ui.goods

import com.progressterra.ipbandroidview.composable.component.GoodsBarComponentEvent
import com.progressterra.ipbandroidview.composable.component.StoreCardComponentState
import com.progressterra.ipbandroidview.composable.component.StoreCardInteractor
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.composable.component.UseGoodsBarComponent

interface GoodsInteractor : StoreCardInteractor, UseGoodsBarComponent {

    fun refresh()

    class Empty : GoodsInteractor {

        override fun handle(event: GoodsBarComponentEvent) = Unit

        override fun handle(event: TextFieldEvent) = Unit

        override fun refresh() = Unit

        override fun onClick(storeCard: StoreCardComponentState) = Unit

        override fun favorite(storeCard: StoreCardComponentState) = Unit
    }
}