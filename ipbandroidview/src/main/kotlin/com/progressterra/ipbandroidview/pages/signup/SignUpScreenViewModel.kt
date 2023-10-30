package com.progressterra.ipbandroidview.pages.signup

import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.features.makephoto.MakePhotoEvent
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
                        editUser = user.copy(phone = user.phone.copy(enabled = false)),
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

    override fun handle(event: TopBarEvent) {
        postEffect(SignUpScreenEffect.OnBack)
    }

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

    override fun handle(event: MakePhotoEvent) {
        when (event) {
            is MakePhotoEvent.Remove -> Unit

            is MakePhotoEvent.Select -> postEffect(SignUpScreenEffect.OpenPhoto(event.photo.url))
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
            val valid =
                currentState.editUser.name.valid() && currentState.editUser.birthday.valid() && !(currentState.editUser.sex == null && IpbAndroidViewSettings.SHOW_SEX_PICKER)
            emitState { it.copy(next = it.next.copy(enabled = valid)) }
        }
    }
}