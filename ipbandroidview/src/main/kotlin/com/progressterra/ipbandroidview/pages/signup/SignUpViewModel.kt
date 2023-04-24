package com.progressterra.ipbandroidview.pages.signup

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.user.FetchPhoneUseCase
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
    private val userValidUseCase: EditUserValidUseCase,
    private val saveDataUseCase: SaveDataUseCase,
    private val fetchPhoneUseCase: FetchPhoneUseCase
) : ViewModel(), ContainerHost<SignUpState, SignUpEvent>, UseSignUp {

    override val container = container<SignUpState, SignUpEvent>(SignUpState())

    fun refresh() = intent {
        reduce { state.updateScreenState(ScreenState.LOADING) }
        fetchPhoneUseCase().onSuccess {
            reduce {
                state.updatePhone(it).updatePhoneEnabled(false)
                    .updateScreenState(ScreenState.SUCCESS)
            }
        }.onFailure {
            reduce { state.updateScreenState(ScreenState.ERROR) }
        }
    }

    override fun handle(event: TopBarEvent) = intent {
        when (event) {
            is TopBarEvent.Back -> postSideEffect(SignUpEvent.OnBack)
        }
    }

    override fun handle(event: ButtonEvent) = intent {
        when (event.id) {
            "auth" -> when (event) {
                is ButtonEvent.Click -> {
                    saveDataUseCase(state.editUser).onSuccess {
                        postSideEffect(SignUpEvent.OnNext)
                    }
                }
            }

            "skip" -> when (event) {
                is ButtonEvent.Click -> postSideEffect(SignUpEvent.OnSkip)
            }
        }
    }

    override fun handle(event: TextFieldEvent) = blockingIntent {
        when (event) {
            is TextFieldEvent.TextChanged -> {
                when (event.id) {
                    "name" -> state.updateName(event.text)
                    "email" -> state.updateEmail(event.text)
                    "birthday" -> state.updateBirthday(event.text)
                    "citizenship" -> state.updateCitizenship(event.text)
                    "address" -> state.updateAddress(event.text)
                    "passport" -> state.updatePassport(event.text)
                    "passportProvider" -> state.updatePassportProvider(event.text)
                    "passportProviderCode" -> state.updatePassportProviderCode(event.text)
                    "patent" -> state.updatePatent(event.text)
                }
            }

            is TextFieldEvent.Action -> Unit
            is TextFieldEvent.AdditionalAction -> when (event.id) {
                "name" -> state.updateName("")
                "email" -> state.updateEmail("")
                "birthday" -> Unit
                "citizenship" -> state.updateCitizenship("")
                "address" -> state.updateAddress("")
                "passport" -> state.updatePassport("")
                "passportProvider" -> state.updatePassportProvider("")
                "passportProviderCode" -> state.updatePassportProviderCode("")
                "patent" -> state.updatePatent("")
            }
        }
        valid()
    }

    private fun valid() = intent {
        val valid = userValidUseCase(state.editUser).isSuccess
        reduce { state.updateAuthEnabled(valid) }
    }
}