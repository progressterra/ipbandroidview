package com.progressterra.ipbandroidview.pages.signup

import com.progressterra.ipbandroidview.features.makephoto.MakePhotoEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.user.FetchUserUseCase
import com.progressterra.ipbandroidview.processes.user.SaveDataUseCase
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.widgets.edituser.EditUserValidUseCase

class SignUpViewModel(
    private val editUserValidUseCase: EditUserValidUseCase,
    private val saveDataUseCase: SaveDataUseCase,
    private val fetchUserUseCase: FetchUserUseCase
) : BaseViewModel<SignUpState, SignUpEvent>(SignUpState()), UseSignUp {

    fun refresh() = onBackground {
        emitState { it.copy(screenState = ScreenState.LOADING) }
        fetchUserUseCase().onSuccess { user ->
            emitState {
                it.copy(
                    editUser = user.copy(phone = user.phone.copy(enabled = false)),
                    screenState = ScreenState.SUCCESS
                )
            }
        }.onFailure {
            emitState { it.copy(screenState = ScreenState.ERROR) }
        }
    }

    override fun handle(event: TopBarEvent) = onBackground {
        postEffect(SignUpEvent.OnBack)
    }

    override fun handle(event: ButtonEvent) = onBackground {
        when (event.id) {
            "auth" -> saveDataUseCase(state.value.editUser).onSuccess {
                postEffect(SignUpEvent.OnNext)
            }

            "skip" -> postEffect(SignUpEvent.OnSkip)
        }
    }

    override fun handle(event: MakePhotoEvent) = onBackground {
        when (event) {
            is MakePhotoEvent.Remove -> Unit

            is MakePhotoEvent.Select -> postEffect(SignUpEvent.OpenPhoto(event.photo.url))
        }
    }


    override fun handle(event: TextFieldEvent) = onUi {
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
            is TextFieldEvent.AdditionalAction -> when (event.id) {
                "name" -> emitState {
                    it.copy(
                        editUser = it.editUser.copy(name = it.editUser.name.copy(text = ""))
                    )
                }

                "email" -> emitState {
                    it.copy(
                        editUser = it.editUser.copy(email = it.editUser.email.copy(text = ""))
                    )
                }

                "birthday" -> Unit
            }
        }
        valid()
    }


    private fun valid() = onBackground {
        val valid = editUserValidUseCase(state.value.editUser).isSuccess
        emitState { it.copy(next = it.next.copy(enabled = valid)) }
    }
}