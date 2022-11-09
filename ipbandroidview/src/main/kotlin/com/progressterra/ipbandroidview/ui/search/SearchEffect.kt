package com.progressterra.ipbandroidview.ui.search

import com.progressterra.ipbandroidview.dto.Goods

sealed class SearchEffect {

    @Suppress("unused")
    class GoodsDetails(val goods: Goods) : SearchEffect()

    object Search : SearchEffect()
}