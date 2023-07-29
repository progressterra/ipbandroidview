package com.progressterra.ipbandroidview.pages.profiledetails

import com.progressterra.ipbandroidview.features.authprofile.AuthProfileEvent
import com.progressterra.ipbandroidview.features.makephoto.MakePhotoEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.user.FetchUserUseCase
import com.progressterra.ipbandroidview.processes.user.SaveDataUseCase
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.widgets.edituser.EditUserValidUseCase

class ProfileDetailsViewModel(
    private val saveUseCase: SaveDataUseCase,
    private val fetchUserUseCase: FetchUserUseCase,
    private val editUserValidUseCase: EditUserValidUseCase
) : BaseViewModel<ProfileDetailsState, ProfileDetailsEvent>(), UseProfileDetails {

    override fun createInitialState() = ProfileDetailsState()

    fun refresh() {
        onBackground {
            this.emitState { ProfileDetailsState() }
            fetchUserUseCase().onSuccess { editUser ->
                this.emitState {
                    it.copy(
                        editUser = editUser.copy(
                            name = editUser.name.copy(enabled = false),
                            email = editUser.email.copy(enabled = false),
                            birthday = editUser.birthday.copy(enabled = false),
                            phone = editUser.phone.copy(enabled = false)
                        ), screen = ScreenState.SUCCESS
                    )
                }
            }.onFailure { this.emitState { it.copy(screen = ScreenState.ERROR) } }
        }
    }

    override fun handle(event: StateBoxEvent) {
        refresh()

    }

    override fun handle(event: AuthProfileEvent) = Unit


    override fun handle(event: ButtonEvent) {
        onBackground {
            when (event.id) {
                "save" -> saveUseCase(currentState.editUser).onSuccess {
                    this.emitState {
                        it.copy(
                            editUser = it.editUser.copy(
                                name = it.editUser.name.copy(enabled = false),
                                email = it.editUser.email.copy(enabled = false),
                                birthday = it.editUser.birthday.copy(enabled = false),
                            ),
                            editButton = it.editButton.copy(editing = !currentState.editButton.editing)
                        )
                    }
                }

                "edit" -> this.emitState {
                    it.copy(
                        editUser = it.editUser.copy(
                            name = it.editUser.name.copy(enabled = true),
                            email = it.editUser.email.copy(enabled = true),
                            birthday = it.editUser.birthday.copy(enabled = true),
                        ),
                        editButton = it.editButton.copy(editing = !currentState.editButton.editing)
                    )
                }

                "cancel" -> refresh()
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(ProfileDetailsEvent.Back)
    }

    override fun handle(event: MakePhotoEvent) {
        when (event) {
            is MakePhotoEvent.Remove -> Unit
            is MakePhotoEvent.Select -> postEffect(ProfileDetailsEvent.OpenPhoto(event.photo.url))
        }
    }

    override fun handle(event: TextFieldEvent) {
        when (event) {
            is TextFieldEvent.TextChanged -> {
                when (event.id) {
                    "name" -> emitState {
                        it.copy(editUser = it.editUser.copy(name = it.editUser.name.copy(text = event.text)))
                    }

                    "email" -> emitState {
                        it.copy(editUser = it.editUser.copy(email = it.editUser.email.copy(text = event.text)))
                    }

                    "birthday" -> emitState {
                        it.copy(
                            editUser = it.editUser.copy(
                                birthday = it.editUser.birthday.copy(
                                    text = event.text
                                )
                            )
                        )
                    }
                }
            }

            is TextFieldEvent.Action -> Unit
            is TextFieldEvent.AdditionalAction -> when (event.id) {
                "name" -> emitState {
                    it.copy(
                        editUser = it.editUser.copy(
                            name = it.editUser.name.copy(
                                text = ""
                            )
                        )
                    )
                }

                "email" -> emitState {
                    it.copy(
                        editUser = it.editUser.copy(
                            email = it.editUser.email.copy(
                                text = ""
                            )
                        )
                    )
                }

                "birthday" -> Unit
            }
        }
        valid()
    }

    private fun valid() {
        onBackground {
            val valid = editUserValidUseCase(currentState.editUser).isSuccess
            this.emitState {
                it.copy(
                    editButton = it.editButton.copy(
                        save = it.editButton.save.copy(
                            enabled = valid
                        )
                    )
                )
            }
        }
    }
}