package com.progressterra.ipbandroidview.pages.profile

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.IsFull
import com.progressterra.ipbandroidview.features.authprofile.AuthProfileState
import com.progressterra.ipbandroidview.features.profilebutton.ProfileButtonState
import com.progressterra.ipbandroidview.shared.IsEmpty
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.processors.IpbSubState

@Immutable
data class ProfileState(
    val isAuthorized: Boolean = false,
    @IpbSubState val unAuth: ButtonState = ButtonState(),
    val authProfileState: AuthProfileState = AuthProfileState(),
    val screenState: ScreenState = ScreenState.LOADING,
    @IpbSubState val orders: ProfileButtonState = ProfileButtonState(
        id = "orders"
    ),
    @IpbSubState val support: ProfileButtonState = ProfileButtonState(
        id = "support"
    ),
    @IpbSubState val favorites: ProfileButtonState = ProfileButtonState(
        id = "favorites"
    ),
    @IpbSubState val documents: ProfileButtonState = ProfileButtonState(
        id = "documents"
    ),
    @IpbSubState val logout: ProfileButtonState = ProfileButtonState(
        id = "logout"
    ),
    val docNotification: CounterNotification = CounterNotification()
) {

    @Immutable
    data class CounterNotification(
        val count: Int = 0,
        val max: Int = 0
    ) : IsEmpty, IsFull {

        override fun isFull(): Boolean = count == max

        override fun isEmpty(): Boolean = count == 0 && max == 0
    }

    fun uNotification(newNotification: CounterNotification) =
        copy(docNotification = newNotification)

    fun uProfile(newProfileState: AuthProfileState) =
        copy(authProfileState = newProfileState)

    fun uScreenState(newScreenState: ScreenState) = copy(screenState = newScreenState)

    fun uIsAuthorized(newIsAuthorized: Boolean) = copy(isAuthorized = newIsAuthorized)
}