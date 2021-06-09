package com.progressterra.ipbandroidview.ui.login.settings

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonalSettings(
    val skipRegistrationButton: Boolean = false,
    val shortSexDescription: Boolean = false,
    val emailDescription: Boolean = false,
    val lastNameAttentionColor: Boolean = false
) : Parcelable
