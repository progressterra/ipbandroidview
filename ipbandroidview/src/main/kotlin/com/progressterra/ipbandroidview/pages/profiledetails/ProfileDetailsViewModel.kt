package com.progressterra.ipbandroidview.pages.profiledetails

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.authprofile.AuthProfileEvent
import com.progressterra.ipbandroidview.features.makephoto.MakePhotoEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.user.FetchUserUseCase
import com.progressterra.ipbandroidview.processes.user.SaveDataUseCase
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.widgets.edituser.EditUserValidUseCase
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class ProfileDetailsViewModel(
    private val saveUseCase: SaveDataUseCase,
    private val fetchUserUseCase: FetchUserUseCase,
    private val editUserValidUseCase: EditUserValidUseCase
) : ViewModel(), ContainerHost<ProfileDetailsState, ProfileDetailsEvent>, UseProfileDetails {

    override val container =
        container<ProfileDetailsState, ProfileDetailsEvent>(ProfileDetailsState())

    fun refresh() {
        intent {
            reduce { ProfileDetailsState() }
            fetchUserUseCase().onSuccess {
                reduce {
                    state.uEditUser(it)
                        .uPhoneEnabled(false)
                        .uEmailEnabled(false)
                        .uBirthdayEnabled(false)
                        .uNameEnabled(false)
                }
                reduce { state.uScreenState(ScreenState.SUCCESS) }
            }.onFailure { reduce { state.uScreenState(ScreenState.ERROR) } }
        }
    }

    override fun handle(event: StateBoxEvent) {
        intent {
            refresh()
        }
    }

    override fun handle(event: AuthProfileEvent) {
        intent {
            when (event) {
                is AuthProfileEvent.Click -> Unit
            }
        }
    }


    override fun handle(event: ButtonEvent) {
        intent {
            when (event.id) {
                "save" -> saveUseCase(state.editUser).onSuccess {
                    reduce {
                        state.startCancelEdit().uEmailEnabled(false)
                            .uNameEnabled(false).uBirthdayEnabled(false)
                    }
                }

                "edit" -> {
                    reduce {
                        state.startCancelEdit().uEmailEnabled(true)
                            .uNameEnabled(true).uBirthdayEnabled(true)
                    }
                }

                "cancel" -> refresh()
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        intent {
            postSideEffect(ProfileDetailsEvent.Back)
        }
    }

    override fun handle(event: MakePhotoEvent) {
        intent {
            when (event) {
                is MakePhotoEvent.Remove -> Unit

                is MakePhotoEvent.Select -> postSideEffect(ProfileDetailsEvent.OpenPhoto(event.photo.url))
            }
        }
    }


    override fun handle(event: TextFieldEvent) {
        blockingIntent {
            when (event) {
                is TextFieldEvent.TextChanged -> {
                    when (event.id) {
                        "name" -> reduce { state.uName(event.text) }
                        "email" -> reduce { state.uEmail(event.text) }
                        "birthday" -> reduce { state.uBirthday(event.text) }
                    }
                }

                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> when (event.id) {
                    "name" -> reduce { state.uName("") }
                    "email" -> reduce { state.uEmail("") }
                    "birthday" -> Unit
                }
            }
            valid()
        }
    }

    private fun valid() {
        intent {
            val valid = editUserValidUseCase(state.editUser).isSuccess
            reduce { state.uSaveEnabled(valid) }
        }
    }
}