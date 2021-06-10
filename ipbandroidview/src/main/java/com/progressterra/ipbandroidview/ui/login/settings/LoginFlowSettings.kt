package com.progressterra.ipbandroidview.ui.login.settings

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginFlowSettings(
    val phoneNumberSettings: PhoneNumberSettings = PhoneNumberSettings(),
    val confirmCodeSettings: ConfirmCodeSettings = ConfirmCodeSettings(),
    val personalSettings: PersonalSettings = PersonalSettings()
) : Parcelable
