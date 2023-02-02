package com.progressterra.ipbandroidview.ui.catalog

import com.progressterra.ipbandroidview.model.store.CategoryWithSubcategories

interface CatalogInteractor {

    fun refresh()

    fun openCategory(categoryWithSubcategories: CategoryWithSubcategories)

    fun search()

    fun editKeyword(keyword: String)

    fun clear()

    class Empty : CatalogInteractor {

        override fun refresh() = Unit

        override fun openCategory(categoryWithSubcategories: CategoryWithSubcategories) = Unit

        override fun search() = Unit

        override fun editKeyword(keyword: String) = Unit

        override fun clear() = Unit
    }
}