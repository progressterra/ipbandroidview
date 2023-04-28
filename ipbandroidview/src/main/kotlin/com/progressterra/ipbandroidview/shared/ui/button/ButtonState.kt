package com.progressterra.ipbandroidview.shared.ui.button

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.progressterra.processors.IpbState
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
@IpbState
data class ButtonState(
    val id: String = "",
    val enabled: Boolean = true
) : Parcelable