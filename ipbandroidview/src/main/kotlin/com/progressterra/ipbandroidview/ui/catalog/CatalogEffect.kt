package com.progressterra.ipbandroidview.ui.catalog

sealed class CatalogEffect {

    object Back : CatalogEffect()

    class SubCatalog(val id: String) : CatalogEffect()

    class GoodsCard(val id: String) : CatalogEffect()
}
