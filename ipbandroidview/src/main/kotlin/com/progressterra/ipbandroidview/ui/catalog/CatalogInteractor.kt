package com.progressterra.ipbandroidview.ui.catalog

import com.progressterra.ipbandroidview.ui.search.SearchInteractor

interface CatalogInteractor : SearchInteractor {

    fun goodsDetails(id: String)

    fun category(id: String)

    class Empty : CatalogInteractor {

        override fun search() = Unit

        override fun favorite(id: String, favorite: Boolean) = Unit

        override fun back() = Unit

        override fun refresh() = Unit

        override fun goodsDetails(id: String) = Unit

        override fun category(id: String) = Unit
    }
}