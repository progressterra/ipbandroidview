package com.progressterra.ipbandroidview.pages.profile

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.authprofile.AuthProfileEvent
import com.progressterra.ipbandroidview.features.profilebutton.ProfileButtonEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

class ProfileViewModel : ViewModel(), ContainerHost<ProfileState, ProfileEvent>, UseProfile {

    override val container = container<ProfileState, ProfileEvent>(ProfileState())

    override fun handle(event: AuthProfileEvent) = intent {
        when (event) {
            is AuthProfileEvent.Click -> postSideEffect(ProfileEvent.Details)
        }
    }

    override fun handle(event: ProfileButtonEvent) = intent {
        when (event) {
            is ProfileButtonEvent.Click -> when (event.id) {
                "logout" -> postSideEffect(ProfileEvent.Logout)
                "delete" -> Unit
                "orders" -> postSideEffect(ProfileEvent.Orders)
                "favorites" -> postSideEffect(ProfileEvent.Favorites)
                "support" -> postSideEffect(ProfileEvent.Support)
            }
        }
    }

    override fun handle(event: TopBarEvent) = Unit

    override fun handle(event: ButtonEvent) = intent {
        when (event) {
            is ButtonEvent.Click -> when (event.id) {
                "auth" -> postSideEffect(ProfileEvent.Auth)
            }
        }
    }
}