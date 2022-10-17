package com.progressterra.ipbandroidview.ui.audits

import android.os.Parcelable
import com.progressterra.ipbandroidview.composable.stats.ChecklistStats
import kotlinx.parcelize.Parcelize

@Parcelize
data class Document(
    val documentId: String,
    val checklistId: String,
    val placeId: String,
    val name: String,
    val address: String,
    val checkCounter: Int,
    val repetitiveness: String,
    val lastTimeChecked: String,
    val finishDate: String,
    val stats: ChecklistStats,
    val done: Boolean
) : Parcelable
