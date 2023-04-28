package com.progressterra.ipbandroidview.pages.signup

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.makephoto.MakePhotoEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.media.MakePhotoUseCase
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
    private val makePhotoUseCase: MakePhotoUseCase,
    private val fetchPhoneUseCase: FetchPhoneUseCase
) : ViewModel(), ContainerHost<SignUpState, SignUpEvent>, UseSignUp {

    override val container = container<SignUpState, SignUpEvent>(SignUpState())

    fun refresh() = intent {
        reduce { state.uScreenState(ScreenState.LOADING) }
        fetchPhoneUseCase().onSuccess {
            reduce {
                state.uPhone(it).uPhoneEnabled(false).uScreenState(ScreenState.SUCCESS)
            }
        }.onFailure {
            reduce { state.uScreenState(ScreenState.ERROR) }
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

            "makePhoto" -> makePhotoUseCase().onSuccess {
                reduce { state.addPhoto(it) }
            }
        }
    }

    override fun handle(event: MakePhotoEvent) = intent {
        when (event) {
            is MakePhotoEvent.Remove -> reduce { state.removePhoto(event.photo) }
            is MakePhotoEvent.Select -> postSideEffect(SignUpEvent.OpenPhoto(event.photo))
        }
    }


    override fun handle(event: TextFieldEvent) = blockingIntent {
        when (event) {
            is TextFieldEvent.TextChanged -> {
                when (event.id) {
                    "name" -> reduce { state.uName(event.text) }
                    "email" -> reduce { state.uEmail(event.text) }
                    "birthday" -> reduce { state.uBirthday(event.text) }
                    "citizenship" -> reduce { state.uCitizenship(event.text) }
                    "address" -> reduce { state.uAddress(event.text) }
                    "passport" -> if (event.text.length <= 10) reduce { state.uPassport(event.text) }
                    "passportProvider" -> reduce { state.uPassportProvider(event.text) }
                    "passportProviderCode" -> if (event.text.length <= 6) reduce {
                        state.uPassportProviderCode(
                            event.text
                        )
                    }

                    "patent" -> reduce { state.uPatent(event.text) }
                }
            }

            is TextFieldEvent.Action -> Unit
            is TextFieldEvent.AdditionalAction -> when (event.id) {
                "name" -> reduce { state.uName("") }
                "email" -> reduce { state.uEmail("") }
                "birthday" -> Unit
                "citizenship" -> reduce { state.uCitizenship("") }
                "address" -> reduce { state.uAddress("") }
                "passport" -> reduce { state.uPassport("") }
                "passportProvider" -> reduce { state.uPassportProvider("") }
                "passportProviderCode" -> reduce { state.uPassportProviderCode("") }
                "patent" -> reduce { state.uPatent("") }
            }
        }
        valid()
    }

    private fun valid() = intent {
        val valid = userValidUseCase(state.editUser).isSuccess
        reduce { state.uAuthEnabled(valid) }
    }
}