package com.progressterra.ipbandroidview.components.stats

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class ChecklistStats(
    val total: Int,
    val successful: Int,
    val failed: Int,
    val remaining: Int
) : Parcelable