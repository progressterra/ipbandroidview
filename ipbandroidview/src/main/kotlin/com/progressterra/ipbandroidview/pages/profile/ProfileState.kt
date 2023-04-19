package com.progressterra.ipbandroidview.pages.profile

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.authprofile.AuthProfileState
import com.progressterra.ipbandroidview.features.profilebutton.ProfileButtonState
import com.progressterra.ipbandroidview.features.unauthprofile.UnAuthProfileState
import com.progressterra.ipbandroidview.shared.ScreenState

@Immutable
data class ProfileState(
    val stateBox: ScreenState = ScreenState.LOADING,
    val isAuthorized: Boolean = false,
    val unAuthProfileState: UnAuthProfileState = UnAuthProfileState(),
    val authProfileState: AuthProfileState = AuthProfileState(),
    val myOrders: ProfileButtonState = ProfileButtonState(),
    val support: ProfileButtonState = ProfileButtonState(),
    val myFavorites: ProfileButtonState = ProfileButtonState(),
    val logout: ProfileButtonState = ProfileButtonState(),
    val deleteAccount: ProfileButtonState = ProfileButtonState()
)