package com.progressterra.ipbandroidview.features.ordertracking

import android.os.Parcelable
import com.progressterra.ipbandroidapi.api.cart.models.TypeStatusOrder
import kotlinx.parcelize.Parcelize


@Parcelize
data class OrderTrackingState(
    val status: TypeStatusOrder = TypeStatusOrder.CANCELED,
    val number: String = ""
) : Parcelable