package com.progressterra.ipbandroidview.signup

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.isEmail
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class SignUpViewModel : ViewModel(), ContainerHost<SignUpState, SignUpEffect>, SignUpInteractor {

    override val container: Container<SignUpState, SignUpEffect> = container(SignUpState())

    override fun onBack() = intent {
        postSideEffect(SignUpEffect.Back)
    }

    override fun onSkip() = intent {
        postSideEffect(SignUpEffect.Skip)
    }

    override fun onNext() = intent {
        if (state.isDataValid)
            postSideEffect(SignUpEffect.Next)
        else
            postSideEffect(SignUpEffect.Toast(R.string.invalid_data))
    }

    override fun onBirthday(birthday: String) = intent {
        reduce { state.copy(birthday = birthday) }
        checkDataValidity()
    }

    override fun onEmail(email: String) = intent {
        reduce {
            state.copy(email = email)
        }
        checkDataValidity()
    }

    override fun onName(name: String) = intent {
        reduce {
            state.copy(name = name)
        }
        checkDataValidity()
    }

    private fun checkDataValidity() = intent {
        reduce {
            state.copy(isDataValid = state.name.isNotBlank() && state.birthday.isNotBlank() && state.email.isEmail())
        }
    }
}