package com.progressterra.ipbandroidview.ui.referral

import com.progressterra.ipbandroidview.core.ScreenState

data class ReferralState(
    val promoCode: String = "",
    val screenState: ScreenState = ScreenState.LOADING
)
