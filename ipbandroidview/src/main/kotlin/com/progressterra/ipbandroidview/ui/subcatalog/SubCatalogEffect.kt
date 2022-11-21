package com.progressterra.ipbandroidview.ui.subcatalog

import com.progressterra.ipbandroidview.model.Category


sealed class SubCatalogEffect {

    class SubCatalog(val subCategory: Category) : SubCatalogEffect()

    class Goods(val categoryId: String) : SubCatalogEffect()
}
