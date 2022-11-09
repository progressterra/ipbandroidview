package com.progressterra.ipbandroidview.ui.search

sealed class SearchEffect {

    class GoodsDetails(val id: String) : SearchEffect()
}
