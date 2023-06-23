package com.progressterra.ipbandroidview.features.orderid

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class OrderIdState(
    val id: String = "",
    val success: Boolean = false
) : Parcelable
