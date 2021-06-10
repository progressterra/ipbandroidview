package com.progressterra.ipbandroidview.ui.login.settings

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonalSettings(
    val enableSkipRegistrationButton: Boolean = false,
    val enableSexShortDescription: Boolean = false,
    val enableEmailDescription: Boolean = false,
    val setLastNameAttentionColor: Boolean = false
) : Parcelable
