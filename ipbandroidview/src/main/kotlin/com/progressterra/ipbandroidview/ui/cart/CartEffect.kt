package com.progressterra.ipbandroidview.ui.cart

import com.progressterra.ipbandroidview.model.Goods

sealed class CartEffect {

    class GoodsDetails(val goods: Goods) : CartEffect()

    object Auth : CartEffect()

    object Next : CartEffect()
}
