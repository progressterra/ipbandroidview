package com.progressterra.ipbandroidview.pages.profile

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.authprofile.AuthProfileEvent
import com.progressterra.ipbandroidview.features.profilebutton.ProfileButtonEvent
import com.progressterra.ipbandroidview.features.profilebutton.enabled
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.user.FetchUserProfileUseCase
import com.progressterra.ipbandroidview.processes.user.LogoutUseCase
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProfileViewModel(
    private val fetchUserProfileUseCase: FetchUserProfileUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val documentsNotification: DocumentsNotificationUseCase
) : ViewModel(), ContainerHost<ProfileState, ProfileEvent>, UseProfile {

    override val container = container<ProfileState, ProfileEvent>(ProfileState())

    fun refresh() {
        intent {
            reduce { ProfileState.screenState.set(state, ScreenState.LOADING) }
            var isSuccess = true
            fetchUserProfileUseCase().onSuccess {
                reduce { ProfileState.isAuthorized.set(state, true) }
                reduce { ProfileState.authProfileState.set(state, it) }
            }.onFailure {
                isSuccess = false
            }
            documentsNotification().onSuccess {
                reduce { ProfileState.docNotification.set(state, it) }
                reduce { ProfileState.documents.enabled.set(state, true) }
            }.onFailure {
                reduce { ProfileState.documents.enabled.set(state, false) }
            }
            reduce { ProfileState.screenState.set(state, isSuccess.toScreenState()) }
        }
    }

    override fun handle(event: StateBoxEvent) {
        refresh()
    }

    override fun handle(event: AuthProfileEvent) {
        intent {
            postSideEffect(ProfileEvent.Details)
        }
    }

    override fun handle(event: ProfileButtonEvent) {
        intent {
            when (event.id) {
                "logout" -> {
                    logoutUseCase().onSuccess {
                        postSideEffect(ProfileEvent.Logout)
                    }
                }

                "delete" -> Unit
                "orders" -> postSideEffect(ProfileEvent.Orders)
                "favorites" -> postSideEffect(ProfileEvent.Favorites)
                "support" -> postSideEffect(ProfileEvent.Support)
                "documents" -> postSideEffect(ProfileEvent.Documents)
            }
        }
    }

    override fun handle(event: TopBarEvent) = Unit

    override fun handle(event: ButtonEvent) {
        intent {
            when (event.id) {
                "auth" -> postSideEffect(ProfileEvent.Auth)
            }
        }
    }
}