package com.progressterra.ipbandroidview.ui.documents

import android.os.Parcelable
import com.progressterra.ipbandroidview.composable.stats.ChecklistStats
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Document(
    val documentId: String,
    val checklistId: String,
    val placeId: String,
    val name: String,
    val address: String,
    val checkCounter: Int,
    val finishDate: Date?,
    val stats: ChecklistStats
) : Parcelable
