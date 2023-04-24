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
                    "name" -> reduce { state.updateName(event.text) }
                    "email" -> reduce { state.updateEmail(event.text) }
                    "birthday" -> reduce { state.updateBirthday(event.text) }
                    "citizenship" -> reduce { state.updateCitizenship(event.text) }
                    "address" -> reduce { state.updateAddress(event.text) }
                    "passport" -> if (event.text.length <= 10) reduce { state.updatePassport(event.text) }
                    "passportProvider" -> reduce { state.updatePassportProvider(event.text) }
                    "passportProviderCode" -> if (event.text.length <= 6) reduce { state.updatePassportProviderCode(event.text) }
                    "patent" -> reduce { state.updatePatent(event.text) }
                }
            }

            is TextFieldEvent.Action -> Unit
            is TextFieldEvent.AdditionalAction -> when (event.id) {
                "name" -> reduce { state.updateName("") }
                "email" -> reduce { state.updateEmail("") }
                "birthday" -> Unit
                "citizenship" -> reduce { state.updateCitizenship("") }
                "address" -> reduce { state.updateAddress("") }
                "passport" -> reduce { state.updatePassport("") }
                "passportProvider" -> reduce { state.updatePassportProvider("") }
                "passportProviderCode" -> reduce { state.updatePassportProviderCode("") }
                "patent" -> reduce { state.updatePatent("") }
            }
        }
        valid()
    }

    private fun valid() = intent {
        val valid = userValidUseCase(state.editUser).isSuccess
        reduce { state.updateAuthEnabled(valid) }
    }
}