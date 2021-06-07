package com.progressterra.ipbandroidview.ui.login

import android.os.Parcelable
import com.progressterra.ipbandroidview.utils.DefaultArgsValues
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginSettings(
    val agreementEnabled: Boolean = false,
    val footerEnabled: Boolean = false,
    val footerImageId: Int = DefaultArgsValues.DEFAULT_RES
) : Parcelable