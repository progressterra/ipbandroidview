package com.progressterra.ipbandroidview.pages.signup

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.isEmail
import com.progressterra.ipbandroidview.shared.isNameAndSurname
import com.progressterra.ipbandroidview.shared.isRussianPhoneNumber
import com.progressterra.ipbandroidview.shared.isTestPhoneNumber
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class SignUpViewModel : ViewModel(), ContainerHost<SignUpState, SignUpEvent>, UseSignUp {

    override val container = container<SignUpState, SignUpEvent>(SignUpState())

    override fun handle(event: TopBarEvent) = intent {
        when (event) {
            is TopBarEvent.Back -> postSideEffect(SignUpEvent.OnBack)
        }
    }

    override fun handle(event: ButtonEvent) = intent {
        when (event.id) {
            "auth" -> when (event) {
                is ButtonEvent.Click -> postSideEffect(SignUpEvent.OnNext)
            }

            "skip" -> when (event) {
                is ButtonEvent.Click -> postSideEffect(SignUpEvent.OnSkip)
            }
        }
    }

    override fun handle(event: TextFieldEvent) = blockingIntent {
        when (event.id) {
            "name" -> when (event) {
                is TextFieldEvent.TextChanged -> reduce { state.updateName(event.text) }
                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> Unit
            }

            "email" -> when (event) {
                is TextFieldEvent.TextChanged -> reduce { state.updateEmail(event.text) }
                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> Unit
            }

            "phone" -> when (event) {
                is TextFieldEvent.TextChanged -> reduce { state.updatePhone(event.text) }
                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> Unit
            }

            "birthday" -> when (event) {
                is TextFieldEvent.TextChanged -> reduce { state.updateBirthday(event.text) }
                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> Unit
            }

            "citizenship" -> when (event) {
                is TextFieldEvent.TextChanged -> reduce { state.updateCitizenship(event.text) }
                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> Unit
            }

            "address" -> when (event) {
                is TextFieldEvent.TextChanged -> reduce { state.updateAddress(event.text) }
                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> Unit
            }

            "passport" -> when (event) {
                is TextFieldEvent.TextChanged -> reduce { state.updatePassport(event.text) }
                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> Unit
            }

            "passportProvider" -> when (event) {
                is TextFieldEvent.TextChanged -> reduce { state.updatePassportProvider(event.text) }
                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> Unit
            }

            "passportProviderCode" -> when (event) {
                is TextFieldEvent.TextChanged -> reduce { state.updatePassportProviderCode(event.text) }
                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> Unit
            }

            "patent" -> when (event) {
                is TextFieldEvent.TextChanged -> reduce { state.updatePatent(event.text) }
                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> Unit
            }
        }
        val valid =
            state.editUser.name.text.isNameAndSurname() && state.editUser.email.text.isEmail() && (state.editUser.phone.text.isRussianPhoneNumber() || state.editUser.phone.text.isTestPhoneNumber()) && state.editUser.birthday.text.isNotBlank() && state.editUser.citizenship.text.isNotBlank() && state.editUser.address.text.isNotBlank() && state.editUser.passport.text.isNotBlank() && state.editUser.passportProvider.text.isNotBlank() && state.editUser.passportProviderCode.text.isNotBlank() && state.editUser.patent.text.isNotBlank()
        reduce { state.updateAuthEnabled(valid) }
    }
}