package com.progressterra.ipbandroidview.ui.search

sealed class SearchEffect {

    class GoodsDetails(val goodsId: String) : SearchEffect()

    object Back : SearchEffect()

    object Filters : SearchEffect()
}