package com.progressterra.ipbandroidview.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuditDocument(
    val checklistId: String = "",
    val placeId: String = "",
    val documentId: String? = null,
    val name: String = ""
) : Parcelable {

    fun updateDocumentId(documentId: String) = copy(documentId = documentId)
}
