package com.progressterra.ipbandroidview.ui.subcatalog

import com.progressterra.ipbandroidview.model.store.CategoryWithSubcategories

interface SubCatalogInteractor {

    fun onBack()

    fun onSubCategory(categoryWithSubcategories: CategoryWithSubcategories)

    fun editKeyword(keyword: String)

    fun search()

    fun expandSearch()

    fun clearSearch()

    class Empty : SubCatalogInteractor {

        override fun onBack() = Unit

        override fun onSubCategory(categoryWithSubcategories: CategoryWithSubcategories) = Unit

        override fun editKeyword(keyword: String) = Unit

        override fun search() = Unit

        override fun expandSearch() = Unit

        override fun clearSearch() = Unit
    }
}