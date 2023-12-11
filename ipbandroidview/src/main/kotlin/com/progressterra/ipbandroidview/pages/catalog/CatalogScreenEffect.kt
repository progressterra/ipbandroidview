package com.progressterra.ipbandroidview.pages.catalog


sealed class CatalogScreenEffect {

    class OnItem(val data: String) : CatalogScreenEffect()

    data object OnAuth : CatalogScreenEffect()
}
