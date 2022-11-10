package com.progressterra.ipbandroidview.ui.catalog

import com.progressterra.ipbandroidview.actions.Refresh
import com.progressterra.ipbandroidview.model.Category


interface CatalogInteractor : Refresh {

    fun category(category: Category)

    class Empty : CatalogInteractor {

        override fun refresh() = Unit

        override fun category(category: Category) = Unit
    }
}