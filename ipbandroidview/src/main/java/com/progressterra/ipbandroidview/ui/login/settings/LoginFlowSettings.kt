package com.progressterra.ipbandroidview.ui.login.settings

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginFlowSettings(
    val phoneNumberSettings: PhoneNumberSettings = PhoneNumberSettings(),
    val confirmCodeSettings: ConfirmCodeSettings = ConfirmCodeSettings(),
    val personalSettings: PersonalSettings = PersonalSettings(),
    val newLoginFlow: Boolean = false,
    val enableEmail: Boolean = true,
    val enableSex: Boolean = true,
    val enableBirthDate: Boolean = true,
    val enableName: Boolean = true,
    val enableSurname: Boolean = true
) : Parcelable
