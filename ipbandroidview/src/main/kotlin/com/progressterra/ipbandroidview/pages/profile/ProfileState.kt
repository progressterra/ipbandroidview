package com.progressterra.ipbandroidview.pages.profile

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.authprofile.AuthProfileState
import com.progressterra.ipbandroidview.features.profilebutton.ProfileButtonState
import com.progressterra.ipbandroidview.features.unauthprofile.UnAuthProfileState

@Immutable
data class ProfileState(
    val isAuthorized: Boolean = false,
    val unAuthProfileState: UnAuthProfileState = UnAuthProfileState(),
    val authProfileState: AuthProfileState = AuthProfileState(),
    val orders: ProfileButtonState = ProfileButtonState(
        id = "orders"
    ),
    val support: ProfileButtonState = ProfileButtonState(
        id = "support"
    ),
    val favorites: ProfileButtonState = ProfileButtonState(
        id = "favorites"
    ),
    val logout: ProfileButtonState = ProfileButtonState(
        id = "logout"
    ),
    val deleteAccount: ProfileButtonState = ProfileButtonState(
        id = "delete"
    )
) {

    fun updateIsAuthorized(newIsAuthorized: Boolean) = copy(isAuthorized = newIsAuthorized)
}