package com.progressterra.ipbandroidview.ui.catalog

import com.progressterra.ipbandroidview.actions.Favorite
import com.progressterra.ipbandroidview.actions.OpenDetails
import com.progressterra.ipbandroidview.actions.Refresh

interface CatalogInteractor : Favorite, OpenDetails<String>, Refresh {

    fun openCard(id: String)

    class Empty : CatalogInteractor {

        override fun openCard(id: String) = Unit

        override fun refresh() = Unit

        override fun favorite(id: String) = Unit

        override fun openDetails(key: String) = Unit
    }
}