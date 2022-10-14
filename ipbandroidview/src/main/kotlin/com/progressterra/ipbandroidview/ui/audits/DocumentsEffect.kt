package com.progressterra.ipbandroidview.ui.audits

sealed class DocumentsEffect {

    @Suppress("unused")
    class OnDocumentDetails(val document: Document) : DocumentsEffect()

    object OnOrganizations : DocumentsEffect()
}
