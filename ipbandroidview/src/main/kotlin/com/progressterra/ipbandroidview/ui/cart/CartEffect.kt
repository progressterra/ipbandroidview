package com.progressterra.ipbandroidview.ui.cart

import com.progressterra.ipbandroidview.model.OrderGoods

sealed class CartEffect {

    class GoodsDetails(val goodsId: String) : CartEffect()

    object Auth : CartEffect()

    class Next(val goods: List<OrderGoods>) : CartEffect()
}
