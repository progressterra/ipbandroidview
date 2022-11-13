package com.progressterra.ipbandroidview.ui.cart

sealed class CartEffect {

    @Suppress("unused")
    class GoodsDetails(val goodsId: String) : CartEffect()

    object Auth : CartEffect()

    object Next : CartEffect()
}
