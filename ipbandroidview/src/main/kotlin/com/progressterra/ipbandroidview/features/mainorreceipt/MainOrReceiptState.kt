package com.progressterra.ipbandroidview.features.mainorreceipt

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.processors.IpbSubState
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class MainOrReceiptState(
    @IpbSubState val main: ButtonState = ButtonState(
        id = "main"
    ),
    @IpbSubState val receipt: ButtonState = ButtonState(
        id = "receipt"
    )
) : Parcelable