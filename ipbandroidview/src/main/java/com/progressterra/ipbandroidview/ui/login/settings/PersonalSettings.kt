package com.progressterra.ipbandroidview.ui.login.settings

import android.os.Parcelable
import com.progressterra.ipbandroidview.utils.DEFAULT_RES
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonalSettings(
    val logoId: Int = DEFAULT_RES,
    val enableSkipRegistrationButton: Boolean = false,
    val enableSexShortDescription: Boolean = false,
    val enableEmailDescription: Boolean = false,
    val setLastNameAttentionColor: Boolean = false,
    val enableSex: Boolean = true,
    val enableEmail: Boolean = true
) : Parcelable
