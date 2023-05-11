package com.progressterra.ipbandroidview.ui.orders

interface OrdersInteractor {

    fun onBack()

    fun openGoodsDetails(goodsId: String)

    fun refresh()

    class Empty : OrdersInteractor {

        override fun onBack() = Unit

        override fun openGoodsDetails(goodsId: String) = Unit

        override fun refresh() = Unit
    }
}