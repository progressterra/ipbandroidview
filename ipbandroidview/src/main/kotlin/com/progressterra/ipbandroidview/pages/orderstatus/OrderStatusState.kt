package com.progressterra.ipbandroidview.pages.orderstatus

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.ordernumber.OrderNumberState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class OrderStatusState(
    val id: String = "",
    val number: OrderNumberState = OrderNumberState(),
    val main: ButtonState = ButtonState(id = "main")
) : Parcelable