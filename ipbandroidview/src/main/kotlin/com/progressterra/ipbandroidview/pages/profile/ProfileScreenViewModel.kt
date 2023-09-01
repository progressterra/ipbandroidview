package com.progressterra.ipbandroidview.pages.profile

import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.authprofile.AuthProfileEvent
import com.progressterra.ipbandroidview.features.profilebutton.ProfileButtonEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.user.FetchUserProfileUseCase
import com.progressterra.ipbandroidview.processes.user.LogoutUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class ProfileScreenViewModel(
    private val fetchUserProfileUseCase: FetchUserProfileUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val documentsNotification: DocumentsNotificationUseCase
) : AbstractNonInputViewModel<ProfileScreenState, ProfileScreenEffect>(), UseProfileScreen {

    override fun createInitialState() = ProfileScreenState()

    override fun refresh() {
        onBackground {
            emitState {
                it.copy(screen = it.screen.copy(state = ScreenState.LOADING))
            }
            var isSuccess = true
            fetchUserProfileUseCase().onSuccess { profile ->
                emitState {
                    it.copy(isAuthorized = true, authProfileState = profile)
                }
            }.onFailure {
                isSuccess = false
            }
            documentsNotification().onSuccess { notification ->
                emitState {
                    it.copy(
                        docNotification = notification,
                        documents = it.documents.copy(enabled = true)
                    )
                }
            }.onFailure {
                emitState {
                    it.copy(documents = it.documents.copy(enabled = false))
                }
            }
            emitState {
                it.copy(screen = it.screen.copy(state = isSuccess.toScreenState()))
            }
        }
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }

    override fun handle(event: AuthProfileEvent) {
        postEffect(ProfileScreenEffect.Details)
    }

    override fun handle(event: ProfileButtonEvent) {
        onBackground {
            when (event.id) {
                "logout" -> {
                    logoutUseCase().onSuccess {
                        postEffect(ProfileScreenEffect.Logout)
                    }
                }

                "delete" -> Unit
                "orders" -> postEffect(ProfileScreenEffect.Orders)
                "favorites" -> postEffect(ProfileScreenEffect.Favorites)
                "support" -> postEffect(ProfileScreenEffect.Support)
                "bankCards" -> postEffect(ProfileScreenEffect.BankCards)
                "documents" -> postEffect(ProfileScreenEffect.Documents)
            }
        }
    }

    override fun handle(event: TopBarEvent) = Unit

    override fun handle(event: ButtonEvent) {
        when (event.id) {
            "auth" -> postEffect(ProfileScreenEffect.Auth)
        }
    }
}