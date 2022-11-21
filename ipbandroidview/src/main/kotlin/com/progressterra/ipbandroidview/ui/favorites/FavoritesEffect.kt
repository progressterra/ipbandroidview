package com.progressterra.ipbandroidview.ui.favorites

sealed class FavoritesEffect {

    class GoodsDetails(val goodsId: String) : FavoritesEffect()
}