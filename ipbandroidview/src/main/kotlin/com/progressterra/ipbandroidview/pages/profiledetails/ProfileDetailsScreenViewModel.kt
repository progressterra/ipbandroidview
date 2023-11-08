package com.progressterra.ipbandroidview.pages.profiledetails

import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidview.IpbAndroidViewSettings
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
import kotlinx.coroutines.launch

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
            viewModelScope.launch { }
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
                            soname = editUser.soname.copy(enabled = false),
                            patronymic = editUser.patronymic.copy(enabled = false),
                            sexEnabled = false
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
                                soname = it.editUser.soname.copy(enabled = false),
                                patronymic = it.editUser.patronymic.copy(enabled = false),
                                sexEnabled = false
                            ),
                            editButton = it.editButton.copy(editing = false)
                        )
                    }
                }

                "edit" -> emitState {
                    it.copy(
                        editUser = it.editUser.copy(
                            name = it.editUser.name.copy(enabled = true),
                            email = it.editUser.email.copy(enabled = true),
                            birthday = it.editUser.birthday.copy(enabled = true),
                            soname = it.editUser.soname.copy(enabled = true),
                            patronymic = it.editUser.patronymic.copy(enabled = true),
                            sexEnabled = true
                        ),
                        editButton = it.editButton.copy(editing = true)
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

                    "soname" -> emitState {
                        it.copy(editUser = it.editUser.copy(soname = it.editUser.soname.copy(text = event.text)))
                    }

                    "patronymic" -> emitState {
                        it.copy(
                            editUser = it.editUser.copy(
                                patronymic = it.editUser.patronymic.copy(
                                    text = event.text
                                )
                            )
                        )
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
            val sonameValid = if (
                IpbAndroidViewSettings.MANDATORY_PROFILE_FIELDS.contains("soname") ||
                (IpbAndroidViewSettings.AVAILABLE_PROFILE_FIELDS.contains("soname")
                        && currentState.editUser.soname.text.isNotEmpty())
            ) currentState.editUser.soname.valid() else true
            val patronymicValid = if (
                IpbAndroidViewSettings.MANDATORY_PROFILE_FIELDS.contains("patronymic") ||
                (IpbAndroidViewSettings.AVAILABLE_PROFILE_FIELDS.contains("patronymic")
                        && currentState.editUser.patronymic.text.isNotEmpty())
            ) currentState.editUser.patronymic.valid() else true
            val dateOfBirthValid = if (
                IpbAndroidViewSettings.MANDATORY_PROFILE_FIELDS.contains("dateOfBirth") ||
                (IpbAndroidViewSettings.AVAILABLE_PROFILE_FIELDS.contains("dateOfBirth")
                        && currentState.editUser.birthday.formatByType().isNotEmpty())
            ) currentState.editUser.birthday.valid() else true
            val nameValid = if (
                IpbAndroidViewSettings.MANDATORY_PROFILE_FIELDS.contains("name") ||
                (IpbAndroidViewSettings.AVAILABLE_PROFILE_FIELDS.contains("name")
                        && currentState.editUser.name.text.isNotEmpty())
            ) currentState.editUser.name.valid() else true
            val emailValid = if (
                IpbAndroidViewSettings.MANDATORY_PROFILE_FIELDS.contains("eMailGeneral") ||
                (IpbAndroidViewSettings.AVAILABLE_PROFILE_FIELDS.contains("eMailGeneral")
                        && currentState.editUser.email.text.isNotEmpty())
            ) currentState.editUser.email.valid() else true
            val sexValid = if (
                IpbAndroidViewSettings.MANDATORY_PROFILE_FIELDS.contains("sex")
            ) currentState.editUser.sex != null else true
            val valid =
                dateOfBirthValid && nameValid && emailValid && sexValid && sonameValid && patronymicValid
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