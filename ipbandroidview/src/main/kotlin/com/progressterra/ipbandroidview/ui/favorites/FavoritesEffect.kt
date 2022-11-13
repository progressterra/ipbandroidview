package com.progressterra.ipbandroidview.ui.favorites


sealed class FavoritesEffect {

    @Suppress("unused")
    class GoodsDetails(val goodsId: String) : FavoritesEffect()
}