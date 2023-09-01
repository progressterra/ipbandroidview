package com.progressterra.ipbandroidview.features.ordernumber

import android.os.Parcelable
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import kotlinx.parcelize.Parcelize

@Parcelize

data class OrderNumberState(
    val number: String = "",
    val quantity: Int = 0,
    val address: String = "",
    val success: Boolean = false,
    val order: ButtonState = ButtonState(id = "order")
) : Parcelable
