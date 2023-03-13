package com.progressterra.ipbandroidview.model

import android.os.Parcelable
import com.progressterra.ipbandroidapi.ext.parseToDate
import kotlinx.parcelize.Parcelize

@Parcelize
data class Document(
    val documentId: String,
    val checklistId: String,
    val placeId: String,
    val name: String,
    val address: String,
    val checkCounter: Int,
    val finishDate: String?,
    val isRecentlyFinished: Boolean,
    val stats: ChecklistStats
) : Parcelable {

    fun isFinished(): Boolean = finishDate != null
}
