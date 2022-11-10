package com.progressterra.ipbandroidview.ui.favorites

import com.progressterra.ipbandroidview.dto.Goods


sealed class FavoritesEffect {

    class GoodsDetails(val goods: Goods) : FavoritesEffect()
}