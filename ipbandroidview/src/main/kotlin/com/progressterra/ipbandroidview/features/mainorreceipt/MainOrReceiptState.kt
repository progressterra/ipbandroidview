package com.progressterra.ipbandroidview.features.mainorreceipt

import android.os.Parcelable
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import kotlinx.parcelize.Parcelize


@Parcelize
data class MainOrReceiptState(
    val main: ButtonState = ButtonState(
        id = "main"
    ),
    val receipt: ButtonState = ButtonState(
        id = "receipt"
    )
) : Parcelable