package com.progressterra.ipbandroidview.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuditDocument(
    val checklistId: String = "",
    val placeId: String = "",
    val documentId: String? = null,
    val name: String = ""
) : Parcelable
