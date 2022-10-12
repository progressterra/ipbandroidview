package com.progressterra.ipbandroidview.ui.audits

interface AuditsInteractor {

    fun onRefresh()

    fun onDocumentChecklist(document: Document)

    fun onAudit()

    class Empty : AuditsInteractor {

        override fun onRefresh() = Unit

        override fun onDocumentChecklist(document: Document) = Unit

        override fun onAudit() = Unit
    }
}