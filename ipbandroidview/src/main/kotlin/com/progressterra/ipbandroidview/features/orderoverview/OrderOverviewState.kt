package com.progressterra.ipbandroidview.features.orderoverview

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class OrderOverviewState(
    val quantity: Int = 0,
    val address: String = "",
    val goodsImages: List<String> = emptyList()
) : Parcelable