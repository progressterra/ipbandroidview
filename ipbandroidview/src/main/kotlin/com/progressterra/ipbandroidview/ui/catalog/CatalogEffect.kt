package com.progressterra.ipbandroidview.ui.catalog

import com.progressterra.ipbandroidview.model.SubCategory

@Suppress("unused")
sealed class CatalogEffect {

    class SubCatalog(val subCategory: SubCategory) : CatalogEffect()

    class Goods(val categoryId: String) : CatalogEffect()
}
