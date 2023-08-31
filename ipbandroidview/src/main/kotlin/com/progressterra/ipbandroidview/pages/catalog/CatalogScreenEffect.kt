package com.progressterra.ipbandroidview.pages.catalog


sealed class CatalogScreenEffect {

    class OnItem(val data: String) : CatalogScreenEffect()
}
