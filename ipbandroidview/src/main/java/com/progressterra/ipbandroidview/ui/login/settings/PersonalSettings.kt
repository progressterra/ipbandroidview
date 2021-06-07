package com.progressterra.ipbandroidview.ui.login.settings

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonalSettings(
    val skipRegistrationButton: Boolean = false,
    val skipRegistrationOutlined: Boolean = false
) : Parcelable
