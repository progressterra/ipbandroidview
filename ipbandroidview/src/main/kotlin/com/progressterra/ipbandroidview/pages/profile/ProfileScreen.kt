package com.progressterra.ipbandroidview.pages.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.AuthProfileState
import com.progressterra.ipbandroidview.features.ProfileButtonState
import com.progressterra.ipbandroidview.features.UnAuthProfileState
import com.progressterra.ipbandroidview.features.UseAuthProfile
import com.progressterra.ipbandroidview.features.UseProfileButton
import com.progressterra.ipbandroidview.features.UseUnAuthProfile
import com.progressterra.ipbandroidview.shared.ui.StateBoxState
import com.progressterra.ipbandroidview.shared.ui.UseStateBox

@Immutable
data class ProfileState(
    val stateBox: StateBoxState = StateBoxState(),
    val isAuthorized: Boolean = false,
    val unAuthProfileState: UnAuthProfileState = UnAuthProfileState(),
    val authProfileState: AuthProfileState = AuthProfileState(),
    val myOrders: ProfileButtonState = ProfileButtonState(),
    val support: ProfileButtonState = ProfileButtonState(),
    val myFavorites: ProfileButtonState = ProfileButtonState(),
    val logout: ProfileButtonState = ProfileButtonState(),
    val deleteAccount: ProfileButtonState = ProfileButtonState()
)

interface UseProfile : UseStateBox, UseProfileButton, UseAuthProfile, UseUnAuthProfile

@Composable
fun ProfileScreen(
    state: ProfileState,
    useComponent: UseProfile
) {
    //TODO
}