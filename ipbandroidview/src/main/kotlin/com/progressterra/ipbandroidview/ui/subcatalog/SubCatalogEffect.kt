package com.progressterra.ipbandroidview.ui.subcatalog

import com.progressterra.ipbandroidview.model.SubCategory


sealed class SubCatalogEffect {

    class SubCatalog(val subCategory: SubCategory) : SubCatalogEffect()

    @Suppress("unused")
    class Goods(val categoryId: String) : SubCatalogEffect()
}
