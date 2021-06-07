package com.progressterra.ipbandroidview.ui.login.settings

import android.os.Parcelable
import com.progressterra.ipbandroidview.utils.DefaultArgsValues
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConfirmCodeSettings(
    val enableLogo: Boolean = false,
    val enableFooter: Boolean = false,
    val footerImageId: Int = DefaultArgsValues.DEFAULT_RES
) : Parcelable
