package com.progressterra.ipbandroidview.pages.profile

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.authprofile.AuthProfileState
import com.progressterra.ipbandroidview.features.profilebutton.ProfileButtonState
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.processors.IpbSubState

@Immutable
data class ProfileState(
    val isAuthorized: Boolean = false,
    @IpbSubState val unAuth: ButtonState = ButtonState(),
    val authProfileState: AuthProfileState = AuthProfileState(),
    val screenState: ScreenState = ScreenState.LOADING,
    val orders: ProfileButtonState = ProfileButtonState(
        id = "orders"
    ),
    val support: ProfileButtonState = ProfileButtonState(
        id = "support"
    ),
    val favorites: ProfileButtonState = ProfileButtonState(
        id = "favorites"
    ),
    val documents: ProfileButtonState = ProfileButtonState(
        id = "documents"
    ),
    val logout: ProfileButtonState = ProfileButtonState(
        id = "logout"
    ),
    val deleteAccount: ProfileButtonState = ProfileButtonState(
        id = "delete"
    )
) {

    fun uProfile(newProfileState: AuthProfileState) =
        copy(authProfileState = newProfileState)

    fun uScreenState(newScreenState: ScreenState) = copy(screenState = newScreenState)

    fun uIsAuthorized(newIsAuthorized: Boolean) = copy(isAuthorized = newIsAuthorized)
}