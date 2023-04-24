package com.progressterra.ipbandroidview.pages.favorites

sealed class FavoritesEvent {

    class GoodsDetails(val goodsId: String) : FavoritesEvent()

    object Back : FavoritesEvent()
}