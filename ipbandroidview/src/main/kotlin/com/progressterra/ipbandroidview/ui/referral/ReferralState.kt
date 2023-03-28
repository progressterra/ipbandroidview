package com.progressterra.ipbandroidview.ui.referral

import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.entities.UserInvite

data class ReferralState(
    val userInvite: UserInvite = UserInvite(),
    val screenState: ScreenState = ScreenState.LOADING
)