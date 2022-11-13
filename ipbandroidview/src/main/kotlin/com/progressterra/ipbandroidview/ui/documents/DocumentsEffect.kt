package com.progressterra.ipbandroidview.ui.documents

@Suppress("unused")
sealed class DocumentsEffect {

    class UpdateCounter(val counter: Int) : DocumentsEffect()

    class OpenChecklist(
        val id: String,
        val placeId: String,
        val isDocument: Boolean,
        val name: String
    ) :
        DocumentsEffect()

    object OpenOrganizations : DocumentsEffect()
}
