package com.progressterra.ipbandroidview.ui.subcatalog

import com.progressterra.ipbandroidview.model.store.Category

interface SubCatalogInteractor {

    fun onBack()

    fun onSubCategory(category: Category)

    fun editKeyword(keyword: String)

    fun search()

    fun expandSearch()

    fun clearSearch()

    class Empty : SubCatalogInteractor {

        override fun onBack() = Unit

        override fun onSubCategory(category: Category) = Unit

        override fun editKeyword(keyword: String) = Unit

        override fun search() = Unit

        override fun expandSearch() = Unit

        override fun clearSearch() = Unit
    }
}