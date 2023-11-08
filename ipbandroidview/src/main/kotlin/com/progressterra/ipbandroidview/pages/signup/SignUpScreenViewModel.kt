package com.progressterra.ipbandroidview.pages.signup

import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.user.FetchUserUseCase
import com.progressterra.ipbandroidview.processes.user.SaveDataUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.widgets.edituser.EditUserEvent

class SignUpScreenViewModel(
    private val saveDataUseCase: SaveDataUseCase,
    private val fetchUserUseCase: FetchUserUseCase
) : AbstractNonInputViewModel<SignUpScreenState, SignUpScreenEffect>(), UseSignUpScreen {

    override fun createInitialState() = SignUpScreenState()

    override fun refresh() {
        onBackground {
            emitState { createInitialState() }
            fetchUserUseCase().onSuccess { user ->
                emitState {
                    it.copy(
                        editUser = user,
                        screen = it.screen.copy(state = ScreenState.SUCCESS)
                    )
                }
            }.onFailure {
                emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
            }
            valid()
        }
    }

    override fun handle(event: EditUserEvent) {
        onBackground {
            emitState { it.copy(editUser = it.editUser.copy(sex = event.data)) }
            valid()
        }
    }

    override fun handle(event: TopBarEvent) = Unit
    override fun handle(event: ButtonEvent) {
        onBackground {
            when (event.id) {
                "next" -> saveDataUseCase(currentState.editUser).onSuccess {
                    postEffect(SignUpScreenEffect.OnNext)
                }

                "skip" -> postEffect(SignUpScreenEffect.OnSkip)
            }
        }
    }

    override fun handle(event: TextFieldEvent) {
        when (event) {
            is TextFieldEvent.TextChanged -> {
                when (event.id) {
                    "name" -> emitState {
                        it.copy(
                            editUser = it.editUser.copy(name = it.editUser.name.copy(text = event.text))
                        )
                    }

                    "soname" -> emitState {
                        it.copy(
                            editUser = it.editUser.copy(soname = it.editUser.soname.copy(text = event.text))
                        )
                    }

                    "patronymic" -> emitState {
                        it.copy(
                            editUser = it.editUser.copy(patronymic = it.editUser.patronymic.copy(text = event.text))
                        )
                    }

                    "email" -> emitState {
                        it.copy(
                            editUser = it.editUser.copy(email = it.editUser.email.copy(text = event.text))
                        )
                    }

                    "birthday" -> emitState {
                        it.copy(
                            editUser = it.editUser.copy(birthday = it.editUser.birthday.copy(text = event.text))
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
            emitState { it.copy(next = it.next.copy(enabled = valid)) }
        }
    }
}