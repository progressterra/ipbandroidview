package com.progressterra.ipbandroidview.ui.referral

import androidx.annotation.StringRes

sealed class ReferralEffect {

    object Back : ReferralEffect()

    class Toast(@StringRes val message: Int) : ReferralEffect()
}
