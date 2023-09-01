package com.progressterra.ipbandroidview.pages.orderstatus

import android.os.Parcelable
import com.progressterra.ipbandroidview.features.ordernumber.OrderNumberState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderStatusScreenState(
    val id: String = "",
    val number: OrderNumberState = OrderNumberState(),
    val main: ButtonState = ButtonState(id = "main")
) : Parcelable