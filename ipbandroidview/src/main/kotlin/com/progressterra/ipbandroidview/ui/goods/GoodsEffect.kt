package com.progressterra.ipbandroidview.ui.goods


sealed class GoodsEffect {

    object Filters : GoodsEffect()

    @Suppress("unused")
    class GoodsDetails(val goodsId: String) : GoodsEffect()
}