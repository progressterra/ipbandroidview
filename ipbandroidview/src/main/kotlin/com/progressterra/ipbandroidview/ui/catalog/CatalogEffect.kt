package com.progressterra.ipbandroidview.ui.catalog

import com.progressterra.ipbandroidview.model.CategoryWithSubcategories

sealed class CatalogEffect {

    class SubCatalog(val subCategoryWithSubcategories: CategoryWithSubcategories) : CatalogEffect()

    class Goods(val categoryId: String) : CatalogEffect()

    class Search(val keyword: String) : CatalogEffect()
}
