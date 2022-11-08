package com.progressterra.ipbandroidview.ui.subcatalog


sealed class SubCatalogEffect {

    object Back : SubCatalogEffect()

    class SubCatalog(val id: String) : SubCatalogEffect()

    class GoodsCard(val id: String) : SubCatalogEffect()
}
