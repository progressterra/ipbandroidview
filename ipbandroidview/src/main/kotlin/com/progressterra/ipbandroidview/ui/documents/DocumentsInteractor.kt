package com.progressterra.ipbandroidview.ui.documents

import com.progressterra.ipbandroidview.actions.Refresh

interface DocumentsInteractor : Refresh, OpenDetails<Document> {

    fun openOrganizations()

    class Empty : DocumentsInteractor {

        override fun refresh() = Unit

        override fun openDetails(key: Document) = Unit

        override fun openOrganizations() = Unit
    }
}