package com.progressterra.ipbandroidview.shared.ui.button

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ButtonState(
    val id: String = "",
    val enabled: Boolean = true
) : Parcelable