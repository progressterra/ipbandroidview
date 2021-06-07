package com.progressterra.ipbandroidview.ui.login.settings

import android.os.Parcelable
import com.progressterra.ipbandroidview.ui.login.country.enums.Country
import com.progressterra.ipbandroidview.utils.DefaultArgsValues
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhoneNumberSettings(
    val defaultCountry: String = Country.RUSSIA.name,
    val agreementEnabled: Boolean = false,
    val footerEnabled: Boolean = false,
    val footerImageId: Int = DefaultArgsValues.DEFAULT_RES
) : Parcelable