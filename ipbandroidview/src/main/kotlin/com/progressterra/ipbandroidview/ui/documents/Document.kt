package com.progressterra.ipbandroidview.ui.documents

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.components.stats.ChecklistStats
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class Document(
    val documentId: String,
    val checklistId: String,
    val placeId: String,
    val name: String,
    val address: String,
    val checkCounter: Int,
    val finishDate: String?,
    val stats: ChecklistStats
) : Parcelable
