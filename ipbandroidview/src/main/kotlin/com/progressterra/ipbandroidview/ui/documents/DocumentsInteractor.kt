package com.progressterra.ipbandroidview.ui.documents

import com.progressterra.ipbandroidview.actions.Refresh

interface DocumentsInteractor : Refresh {

    fun onDocumentChecklist(document: Document)

    fun onAudit()

    class Empty : DocumentsInteractor {

        override fun refresh() = Unit

        override fun onDocumentChecklist(document: Document) = Unit

        override fun onAudit() = Unit
    }
}