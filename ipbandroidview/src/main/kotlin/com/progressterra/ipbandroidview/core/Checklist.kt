package com.progressterra.ipbandroidview.core

import android.os.Parcelable
import com.progressterra.ipbandroidview.ui.checklist.Check
import kotlinx.parcelize.Parcelize

@Parcelize
data class Checklist(
    val checklistId: String,
    val placeId: String,
    val name: String,
    val done: Boolean,
    val ongoing: Boolean,
    val repetitiveness: String,
    val lastTimeChecked: String,
    val checks: List<Check>
) : Parcelable
