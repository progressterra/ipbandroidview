package com.progressterra.ipbandroidview.ui.login.settings

import android.os.Parcelable
import com.progressterra.ipbandroidview.ui.login.country.enums.Country
import com.progressterra.ipbandroidview.utils.DEFAULT_RES
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhoneNumberSettings(
    val defaultCountry: String = Country.RUSSIA.name,
    val agreementEnabled: Boolean = false,
    val footerImageId: Int = DEFAULT_RES,
    val headerImageId: Int = DEFAULT_RES,
    val showSkipBtn: Boolean = false
) : Parcelable