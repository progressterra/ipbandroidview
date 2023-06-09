package com.progressterra.ipbandroidview.shared.ui.textfield

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.processors.IpbState
import kotlinx.parcelize.Parcelize

@Immutable
@IpbState
@Parcelize
data class TextFieldState(
    override val id: String = "",
    val text: String = "",
    val hint: String? = null,
    val enabled: Boolean = true,
    val valid: Boolean = true
) : Id, Parcelable