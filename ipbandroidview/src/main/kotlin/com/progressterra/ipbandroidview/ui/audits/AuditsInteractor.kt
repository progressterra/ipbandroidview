package com.progressterra.ipbandroidview.ui.audits

interface AuditsInteractor {

    fun onDocumentDetails(id: String)

    fun onAudit()

    class Empty : AuditsInteractor {

        override fun onDocumentDetails(id: String) = Unit

        override fun onAudit() = Unit
    }
}