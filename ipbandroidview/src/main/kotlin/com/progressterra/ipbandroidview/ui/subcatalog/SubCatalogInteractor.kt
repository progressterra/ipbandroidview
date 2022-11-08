package com.progressterra.ipbandroidview.ui.subcatalog

import com.progressterra.ipbandroidview.ui.search.SearchInteractor

interface SubCatalogInteractor : SearchInteractor {

    fun goodsDetails(id: String)

    fun subCategory(id: String)

    class Empty : SubCatalogInteractor {

        override fun search() = Unit

        override fun goodsDetails(id: String) = Unit

        override fun subCategory(id: String) = Unit

        override fun back() = Unit

        override fun favorite(id: String, favorite: Boolean) = Unit

        override fun refresh() = Unit
    }
}