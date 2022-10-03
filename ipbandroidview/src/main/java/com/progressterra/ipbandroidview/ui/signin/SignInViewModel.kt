package com.progressterra.ipbandroidview.ui.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidapi.user.UserData
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.domain.StartVerificationChannelUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class SignInViewModel(
    private val startVerificationChannelUseCase: StartVerificationChannelUseCase
) : ViewModel(), ContainerHost<SignInState, SignInEffect>, SignInInteractor {

    override val container: Container<SignInState, SignInEffect> = container(SignInState())

    override fun onNext() = intent {
        withContext(Dispatchers.IO) {
            if (startVerificationChannelUseCase.start(state.phoneNumber)) {
                UserData.phone = state.phoneNumber
                postSideEffect(SignInEffect.Next)
            } else
                postSideEffect(SignInEffect.Toast(R.string.wrong_phone))
        }
    }

    override fun onSkip() = intent {
        postSideEffect(SignInEffect.Skip)
    }

    override fun onBack() = intent {
        postSideEffect(SignInEffect.Back)
    }

    override fun onPhoneNumber(phoneNumber: String) {
        Log.d("INTENT", "onPhoneNumber start: ${Thread.currentThread().name}")
        intent {
            Log.d("INTENT", "onPhoneNumber in intent 1: ${Thread.currentThread().name}")
            reduce {
                state.copy(phoneNumber = phoneNumber)
            }
        }
        intent {
            withContext(Dispatchers.IO) {
                Log.d("INTENT", "onPhoneNumber in intent 2: ${Thread.currentThread().name}")
                delay(5000)
            }
        }
        Log.d("INTENT", "onPhoneNumber finished: ${Thread.currentThread().name}")
    }
}