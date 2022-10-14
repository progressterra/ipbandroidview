package com.progressterra.ipbandroidview.ui.audits

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Document(
    val checklistId: String,
    val placeId: String,
    val name: String,
    val address: String,
    val percentage: Int,
    val checkCounter: Int,
    val repetitiveness: String,
    val lastTimeChecked: String,
    val done: Boolean
) : Parcelable
