package com.progressterra.ipbandroidview.ui.documents

import com.progressterra.ipbandroidview.actions.Refresh

interface DocumentsInteractor : Refresh {

    fun openDocument(document: Document)

    fun openOrganizations()

    class Empty : DocumentsInteractor {

        override fun refresh() = Unit

        override fun openOrganizations() = Unit

        override fun openDocument(document: Document) = Unit
    }
}