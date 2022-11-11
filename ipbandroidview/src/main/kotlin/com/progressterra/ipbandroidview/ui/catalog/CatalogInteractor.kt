package com.progressterra.ipbandroidview.ui.catalog

import com.progressterra.ipbandroidview.actions.Details
import com.progressterra.ipbandroidview.actions.Refresh
import com.progressterra.ipbandroidview.model.Category


interface CatalogInteractor : Refresh, Details<Category> {

    class Empty : CatalogInteractor {

        override fun refresh() = Unit

        override fun openDetails(item: Category) = Unit
    }
}