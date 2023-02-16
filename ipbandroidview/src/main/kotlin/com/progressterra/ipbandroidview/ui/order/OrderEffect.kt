package com.progressterra.ipbandroidview.ui.order

import com.progressterra.ipbandroidview.composable.component.OrderProcessingComponentState
import com.progressterra.ipbandroidview.model.PickUpPointInfo

sealed class OrderEffect {

    class Next(val orderProcessingComponentState: OrderProcessingComponentState) : OrderEffect()

    object Back : OrderEffect()

    object City : OrderEffect()

    class PickUp(val pickUpPoints: List<PickUpPointInfo>) : OrderEffect()

    class GoodsDetails(val goodsId: String) : OrderEffect()
}
