package com.progressterra.ipbandroidview.ui.catalog

import com.progressterra.ipbandroidview.model.Category

sealed class CatalogEffect {

    class SubCatalog(val subCategory: Category) : CatalogEffect()

    class Goods(val categoryId: String) : CatalogEffect()
}
