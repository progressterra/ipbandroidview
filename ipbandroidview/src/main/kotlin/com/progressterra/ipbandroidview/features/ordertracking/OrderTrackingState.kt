package com.progressterra.ipbandroidview.features.ordertracking

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidapi.api.cart.models.TypeStatusOrder
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class OrderTrackingState(
    val status: TypeStatusOrder = TypeStatusOrder.CANCELED,
    val number: String = ""
) : Parcelable