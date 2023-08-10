package com.progressterra.ipbandroidview.pages.signup

import com.progressterra.ipbandroidview.features.makephoto.MakePhotoEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.user.FetchUserUseCase
import com.progressterra.ipbandroidview.processes.user.SaveDataUseCase
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

class SignUpViewModel(
    private val saveDataUseCase: SaveDataUseCase,
    private val fetchUserUseCase: FetchUserUseCase
) : BaseViewModel<SignUpState, SignUpEvent>(), UseSignUp {

    override fun createInitialState() = SignUpState()

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

    override fun handle(event: TopBarEvent) {
        postEffect(SignUpEvent.OnBack)
    }

    override fun handle(event: ButtonEvent) = onBackground {
        when (event.id) {
            "next" -> saveDataUseCase(currentState.editUser).onSuccess {
                postEffect(SignUpEvent.OnNext)
            }

            "skip" -> postEffect(SignUpEvent.OnSkip)
        }
    }

    override fun handle(event: MakePhotoEvent) {
        when (event) {
            is MakePhotoEvent.Remove -> Unit

            is MakePhotoEvent.Select -> postEffect(SignUpEvent.OpenPhoto(event.photo.url))
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
        val valid =
            currentState.editUser.name.valid() && currentState.editUser.email.valid() && currentState.editUser.birthday.valid()
        emitState { it.copy(next = it.next.copy(enabled = valid)) }
    }
}