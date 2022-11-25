package com.progressterra.ipbandroidview.ui.order

import com.progressterra.ipbandroidview.model.OrderResult

sealed class OrderEffect {

    class Next(val orderResult: OrderResult) : OrderEffect()

    object Back : OrderEffect()

    object City : OrderEffect()

    object PickUp : OrderEffect()

    class GoodsDetails(val goodsId: String) : OrderEffect()
}
