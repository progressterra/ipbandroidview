package com.progressterra.ipbandroidview.composable.stats

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChecklistStats(
    val total: Int,
    val successful: Int,
    val failed: Int,
    val remaining: Int
) : Parcelable