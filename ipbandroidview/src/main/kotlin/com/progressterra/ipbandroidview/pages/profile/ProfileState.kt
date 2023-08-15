package com.progressterra.ipbandroidview.pages.profile

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.IsFull
import com.progressterra.ipbandroidview.features.authprofile.AuthProfileState
import com.progressterra.ipbandroidview.features.profilebutton.ProfileButtonState
import com.progressterra.ipbandroidview.shared.IsEmpty
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState

@Immutable
data class ProfileState(
    val isAuthorized: Boolean = false,
    val unAuth: ButtonState = ButtonState(),
    val authProfileState: AuthProfileState = AuthProfileState(),
    val screenState: ScreenState = ScreenState.LOADING,
    val orders: ProfileButtonState = ProfileButtonState(id = "orders"),
    val support: ProfileButtonState = ProfileButtonState(id = "support"),
    val favorites: ProfileButtonState = ProfileButtonState(id = "favorites"),
    val documents: ProfileButtonState = ProfileButtonState(id = "documents"),
    val bankCards: ProfileButtonState = ProfileButtonState(id = "bankCards"),
    val logout: ProfileButtonState = ProfileButtonState(id = "logout"),
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

    companion object
}