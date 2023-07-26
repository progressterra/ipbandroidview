package com.progressterra.ipbandroidview.pages.profiledetails

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.authprofile.AuthProfileEvent
import com.progressterra.ipbandroidview.features.editbutton.editing
import com.progressterra.ipbandroidview.features.editbutton.save
import com.progressterra.ipbandroidview.features.makephoto.MakePhotoEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.user.FetchUserUseCase
import com.progressterra.ipbandroidview.processes.user.SaveDataUseCase
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.enabled
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.enabled
import com.progressterra.ipbandroidview.shared.ui.textfield.text
import com.progressterra.ipbandroidview.widgets.edituser.EditUserValidUseCase
import com.progressterra.ipbandroidview.widgets.edituser.birthday
import com.progressterra.ipbandroidview.widgets.edituser.email
import com.progressterra.ipbandroidview.widgets.edituser.name
import com.progressterra.ipbandroidview.widgets.edituser.phone
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
                reduce { ProfileDetailsState.editUser.set(state, it) }
                reduce { ProfileDetailsState.editUser.phone.enabled.set(state, false) }
                reduce { ProfileDetailsState.editUser.email.enabled.set(state, false) }
                reduce { ProfileDetailsState.editUser.birthday.enabled.set(state, false) }
                reduce { ProfileDetailsState.editUser.name.enabled.set(state, false) }
                reduce { ProfileDetailsState.screen.set(state, ScreenState.SUCCESS) }
            }.onFailure { reduce { ProfileDetailsState.screen.set(state, ScreenState.ERROR) } }
        }
    }

    override fun handle(event: StateBoxEvent) {
        intent {
            refresh()
        }
    }

    override fun handle(event: AuthProfileEvent) = Unit


    override fun handle(event: ButtonEvent) {
        intent {
            when (event.id) {
                "save" -> saveUseCase(state.editUser).onSuccess {
                    reduce {
                        ProfileDetailsState.editButton.editing.set(
                            state,
                            !state.editButton.editing
                        )
                    }
                    reduce { ProfileDetailsState.editUser.email.enabled.set(state, false) }
                    reduce { ProfileDetailsState.editUser.birthday.enabled.set(state, false) }
                    reduce { ProfileDetailsState.editUser.name.enabled.set(state, false) }
                }

                "edit" -> {
                    reduce {
                        ProfileDetailsState.editButton.editing.set(
                            state,
                            !state.editButton.editing
                        )
                    }
                    reduce { ProfileDetailsState.editUser.email.enabled.set(state, true) }
                    reduce { ProfileDetailsState.editUser.birthday.enabled.set(state, true) }
                    reduce { ProfileDetailsState.editUser.name.enabled.set(state, true) }
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
                        "name" -> reduce {
                            ProfileDetailsState.editUser.name.text.set(
                                state,
                                event.text
                            )
                        }

                        "email" -> reduce {
                            ProfileDetailsState.editUser.email.text.set(
                                state,
                                event.text
                            )
                        }

                        "birthday" -> reduce {
                            ProfileDetailsState.editUser.birthday.text.set(
                                state,
                                event.text
                            )
                        }
                    }
                }

                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> when (event.id) {
                    "name" -> reduce { ProfileDetailsState.editUser.name.text.set(state, "") }
                    "email" -> reduce { ProfileDetailsState.editUser.email.text.set(state, "") }
                    "birthday" -> Unit
                }
            }
            valid()
        }
    }

    private fun valid() {
        intent {
            val valid = editUserValidUseCase(state.editUser).isSuccess
            reduce { ProfileDetailsState.editButton.save.enabled.set(state, valid) }
        }
    }
}