package com.progressterra.ipbandroidview.ui.signup

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidapi.user.UserData
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.domain.UpdatePersonalInfoUseCase
import com.progressterra.ipbandroidview.ext.isEmail
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate

class SignUpViewModel(
    private val updatePersonalInfoUseCase: UpdatePersonalInfoUseCase
) : ViewModel(), ContainerHost<SignUpState, SignUpEffect>, SignUpInteractor {

    override val container: Container<SignUpState, SignUpEffect> =
        container(SignUpState(phoneNumber = UserData.phone))

    override fun onBack() = intent { postSideEffect(SignUpEffect.Back) }

    override fun onSkip() = intent { postSideEffect(SignUpEffect.Skip) }

    override fun onNext() = intent {
        if (state.isDataValid) {
            updatePersonalInfoUseCase.update(state.name, state.email, state.birthdayDate)
            postSideEffect(SignUpEffect.Next)
        } else postSideEffect(SignUpEffect.Toast(R.string.invalid_data))
    }

    override fun onBirthday(birthday: String, birthdayDate: LocalDate) {
        intent { reduce { state.copy(birthday = birthday, birthdayDate = birthdayDate) } }
        checkDataValidity()
    }

    override fun onEmail(email: String) {
        intent { reduce { state.copy(email = email) } }
        checkDataValidity()
    }

    override fun onName(name: String) {
        intent { reduce { state.copy(name = name) } }
        checkDataValidity()
    }

    override fun openCalendar() = intent { reduce { state.copy(showCalendar = true) } }

    override fun closeCalendar() = intent { reduce { state.copy(showCalendar = false) } }

    private fun checkDataValidity() = intent {
        reduce { state.copy(isDataValid = state.name.isNotBlank() && state.birthday.isNotBlank() && state.email.isEmail()) }
    }
}