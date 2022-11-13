package com.progressterra.ipbandroidview.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class AuditDocument(
    val id: String = "",
    val placeId: String = "",
    val name: String = "",
    val ongoing: Boolean = false,
    val readOrCompleteOnly: Boolean = false
) : Parcelable
