package com.progressterra.ipbandroidview.pages.profile

import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.authprofile.AuthProfileEvent
import com.progressterra.ipbandroidview.features.profilebutton.ProfileButtonEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.user.FetchUserProfileUseCase
import com.progressterra.ipbandroidview.processes.user.LogoutUseCase
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent

class ProfileViewModel(
    private val fetchUserProfileUseCase: FetchUserProfileUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val documentsNotification: DocumentsNotificationUseCase
) : BaseViewModel<ProfileState, ProfileEvent>(), UseProfile {

    override fun createInitialState() = ProfileState()

    fun refresh() {
        onBackground {
            emitState {
                it.copy(screenState = ScreenState.LOADING)
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
                it.copy(screenState = isSuccess.toScreenState())
            }
        }
    }

    override fun handle(event: StateBoxEvent) {
        refresh()
    }

    override fun handle(event: AuthProfileEvent) {
        postEffect(ProfileEvent.Details)
    }

    override fun handle(event: ProfileButtonEvent) {
        onBackground {
            when (event.id) {
                "logout" -> {
                    logoutUseCase().onSuccess {
                        postEffect(ProfileEvent.Logout)
                    }
                }

                "delete" -> Unit
                "orders" -> postEffect(ProfileEvent.Orders)
                "favorites" -> postEffect(ProfileEvent.Favorites)
                "support" -> postEffect(ProfileEvent.Support)
                "bankCards" -> postEffect(ProfileEvent.BankCards)
                "documents" -> postEffect(ProfileEvent.Documents)
            }
        }
    }

    override fun handle(event: TopBarEvent) = Unit

    override fun handle(event: ButtonEvent) {
        when (event.id) {
            "auth" -> postEffect(ProfileEvent.Auth)
        }
    }
}