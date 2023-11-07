package com.progressterra.ipbandroidview.pages.profiledetails

import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.editprofile.EditProfileEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.user.FetchAvatarUseCase
import com.progressterra.ipbandroidview.processes.user.FetchUserUseCase
import com.progressterra.ipbandroidview.processes.user.PickPhotoUseCase
import com.progressterra.ipbandroidview.processes.user.SaveAvatarUseCase
import com.progressterra.ipbandroidview.processes.user.SaveDataUseCase
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.widgets.edituser.EditUserEvent

class ProfileDetailsScreenViewModel(
    private val saveUseCase: SaveDataUseCase,
    private val fetchUserUseCase: FetchUserUseCase,
    private val fetchAvatarUseCase: FetchAvatarUseCase,
    private val pickPhotoUseCase: PickPhotoUseCase,
    private val saveAvatarUseCase: SaveAvatarUseCase,
    private val makeToastUseCase: MakeToastUseCase
) : AbstractNonInputViewModel<ProfileDetailsState, ProfileDetailsScreenEffect>(),
    UseProfileDetailsScreen {

    override fun createInitialState() = ProfileDetailsState()

    override fun handle(event: EditProfileEvent) {
        onBackground {
            pickPhotoUseCase().onSuccess { path ->
                saveAvatarUseCase(path).onSuccess {
                    makeToastUseCase(R.string.success)
                    refresh()
                }.onFailure {
                    makeToastUseCase(R.string.failure)
                }
            }
        }
    }

    override fun refresh() {
        onBackground {
            emitState { createInitialState() }
            var isSuccess = true
            fetchUserUseCase().onSuccess { editUser ->
                emitState {
                    it.copy(
                        editUser = editUser.copy(
                            name = editUser.name.copy(enabled = false),
                            email = editUser.email.copy(enabled = false),
                            birthday = editUser.birthday.copy(enabled = false),
                            phone = editUser.phone.copy(enabled = false)
                        ), screen = it.screen.copy(state = ScreenState.SUCCESS)
                    )
                }
            }.onFailure { isSuccess = false }
            fetchAvatarUseCase().onSuccess { url ->
                emitState {
                    it.copy(editProfile = it.editProfile.copy(profileImage = url))
                }
            }.onFailure { isSuccess = false }
            emitState { it.copy(screen = it.screen.copy(state = isSuccess.toScreenState())) }
        }
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }

    override fun handle(event: EditUserEvent) {
        onBackground {
            emitState { it.copy(editUser = it.editUser.copy(sex = event.data)) }
        }
    }

    override fun handle(event: ButtonEvent) {
        onBackground {
            when (event.id) {
                "save" -> saveUseCase(currentState.editUser).onSuccess {
                    emitState {
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

                "edit" -> emitState {
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
        postEffect(ProfileDetailsScreenEffect.Back)
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
            is TextFieldEvent.AdditionalAction -> Unit
        }
        valid()
    }

    private fun valid() {
        onBackground {
            val valid =
                currentState.editUser.name.valid() && currentState.editUser.birthday.valid()
            emitState {
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