package com.progressterra.ipbandroidview.ui.catalog

import com.progressterra.ipbandroidview.dto.SubCategory

sealed class CatalogEffect {

    class SubCatalog(val subCategory: SubCategory) : CatalogEffect()

    @Suppress("unused")
    class Goods(val categoryId: String) : CatalogEffect()
}
