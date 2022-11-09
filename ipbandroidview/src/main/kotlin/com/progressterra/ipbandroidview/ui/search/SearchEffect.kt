package com.progressterra.ipbandroidview.ui.search

sealed class SearchEffect {

    @Suppress("unused")
    class GoodsDetails(val goodsId: String) : SearchEffect()

    object Search : SearchEffect()

}
