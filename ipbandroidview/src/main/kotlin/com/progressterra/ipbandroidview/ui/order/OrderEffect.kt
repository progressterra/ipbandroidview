package com.progressterra.ipbandroidview.ui.order

import com.progressterra.ipbandroidview.model.store.OrderResult
import com.progressterra.ipbandroidview.model.delivery.PickUpPointInfo

sealed class OrderEffect {

    class Next(val orderResult: OrderResult) : OrderEffect()

    object Back : OrderEffect()

    object City : OrderEffect()

    class PickUp(val pickUpPoints: List<PickUpPointInfo>) : OrderEffect()

    class GoodsDetails(val goodsId: String) : OrderEffect()
}
