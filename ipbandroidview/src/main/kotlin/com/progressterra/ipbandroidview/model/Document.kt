package com.progressterra.ipbandroidview.model

import android.os.Parcelable
import com.progressterra.ipbandroidview.model.ChecklistStats
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
    val stats: ChecklistStats
) : Parcelable {

    fun isFinished(): Boolean = finishDate != null
}
