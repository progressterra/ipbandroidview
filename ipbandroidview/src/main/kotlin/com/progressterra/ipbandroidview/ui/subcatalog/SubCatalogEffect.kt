package com.progressterra.ipbandroidview.ui.subcatalog

import com.progressterra.ipbandroidview.model.store.CategoryWithSubcategories

sealed class SubCatalogEffect {

    object Back : SubCatalogEffect()

    class SubCatalog(val subCategoryWithSubcategories: CategoryWithSubcategories) : SubCatalogEffect()

    class Goods(val categoryId: String) : SubCatalogEffect()

    class Search(val keyword: String) : SubCatalogEffect()
}