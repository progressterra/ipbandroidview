package com.progressterra.ipbandroidview.ui.goods


sealed class GoodsEffect {

    object Back : GoodsEffect()

    class GoodsDetails(val id: String) : GoodsEffect()
}