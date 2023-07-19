package com.progressterra.ipbandroidview.pages.profile

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.authprofile.AuthProfileEvent
import com.progressterra.ipbandroidview.features.profilebutton.ProfileButtonEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.user.FetchUserProfileUseCase
import com.progressterra.ipbandroidview.processes.user.LogoutUseCase
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.UserData
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
            reduce { state.uScreenState(ScreenState.LOADING) }
            var isSuccess = true
            fetchUserProfileUseCase().onSuccess {
                reduce {
                    state.uProfile(it).uIsAuthorized(UserData.clientExist)
                }
            }.onFailure {
                isSuccess = false
            }
            documentsNotification().onSuccess {
                reduce { state.uNotification(it).uDocumentsEnabled(true) }
            }.onFailure {
                reduce { state.uDocumentsEnabled(false) }
            }
            reduce { state.uScreenState(ScreenState.fromBoolean(isSuccess)) }
        }
    }

    override fun handle(event: StateBoxEvent) {
        intent {
            refresh()
        }
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