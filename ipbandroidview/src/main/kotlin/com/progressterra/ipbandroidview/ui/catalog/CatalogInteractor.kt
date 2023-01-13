package com.progressterra.ipbandroidview.ui.catalog

import com.progressterra.ipbandroidview.model.store.Category

interface CatalogInteractor {

    fun refresh()

    fun openCategory(category: Category)

    fun search()

    fun editKeyword(keyword: String)

    fun clear()

    class Empty : CatalogInteractor {

        override fun refresh() = Unit

        override fun openCategory(category: Category) = Unit

        override fun search() = Unit

        override fun editKeyword(keyword: String) = Unit

        override fun clear() = Unit
    }
}