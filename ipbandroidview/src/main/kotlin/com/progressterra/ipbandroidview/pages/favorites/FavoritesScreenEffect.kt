package com.progressterra.ipbandroidview.pages.favorites

sealed class FavoritesScreenEffect {

    class GoodsDetails(val data: String) : FavoritesScreenEffect()

    data object Back : FavoritesScreenEffect()
}