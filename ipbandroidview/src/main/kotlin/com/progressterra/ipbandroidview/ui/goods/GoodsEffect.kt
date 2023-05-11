package com.progressterra.ipbandroidview.ui.goods

sealed class GoodsEffect {

    object Filters : GoodsEffect()

    object Back : GoodsEffect()

    class GoodsDetails(val goodsId: String) : GoodsEffect()
}