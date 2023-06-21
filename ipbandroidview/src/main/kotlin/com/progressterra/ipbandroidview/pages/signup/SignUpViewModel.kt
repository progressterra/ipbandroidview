package com.progressterra.ipbandroidview.pages.signup

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.makephoto.MakePhotoEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.user.FetchUserUseCase
import com.progressterra.ipbandroidview.processes.user.SaveDataUseCase
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
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
class SignUpViewModel(
    private val editUserValidUseCase: EditUserValidUseCase,
    private val saveDataUseCase: SaveDataUseCase,
    private val fetchUserUseCase: FetchUserUseCase
) : ViewModel(), ContainerHost<SignUpState, SignUpEvent>, UseSignUp {

    override val container = container<SignUpState, SignUpEvent>(SignUpState())

    fun refresh() {
        intent {
            reduce { state.uScreenState(ScreenState.LOADING) }
            fetchUserUseCase().onSuccess {
                reduce {
                    state.uEditUser(it).uPhoneEnabled(false).uScreenState(ScreenState.SUCCESS)
                }
            }.onFailure {
                reduce { state.uScreenState(ScreenState.ERROR) }
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        intent {
            postSideEffect(SignUpEvent.OnBack)
        }
    }

    override fun handle(event: ButtonEvent) {
        intent {
            when (event.id) {
                "auth" -> saveDataUseCase(state.editUser).onSuccess {
                    postSideEffect(SignUpEvent.OnNext)
                }

                "skip" -> postSideEffect(SignUpEvent.OnSkip)
            }
        }
    }

    override fun handle(event: MakePhotoEvent) {
        intent {
            when (event) {
                is MakePhotoEvent.Remove -> Unit

                is MakePhotoEvent.Select -> postSideEffect(SignUpEvent.OpenPhoto(event.photo.url))
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
            reduce { state.uAuthEnabled(valid) }
        }
    }
}