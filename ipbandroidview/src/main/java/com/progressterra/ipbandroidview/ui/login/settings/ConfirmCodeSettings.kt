package com.progressterra.ipbandroidview.ui.login.settings

import android.os.Parcelable
import com.progressterra.ipbandroidview.utils.DEFAULT_RES
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConfirmCodeSettings(
    val enableLogo: Boolean = false,
    val enableFooter: Boolean = false,
    val logoImageResId: Int = DEFAULT_RES,
    val footerImageResId: Int = DEFAULT_RES,
    val outlinedCircles: Boolean = false
) : Parcelable
