package com.progressterra.ipbandroidview.shared.ui.button

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class ButtonState(
    val id: String = "",
    val enabled: Boolean = true
) : Parcelable