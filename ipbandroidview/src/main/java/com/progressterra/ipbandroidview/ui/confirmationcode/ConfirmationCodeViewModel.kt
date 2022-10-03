package com.progressterra.ipbandroidview.ui.confirmationcode

import android.util.Log
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidapi.user.UserData
import com.progressterra.ipbandroidview.domain.EndVerificationChannelUseCase
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

class ConfirmationCodeViewModel(
    private val startVerificationChannelUseCase: StartVerificationChannelUseCase,
    private val endVerificationChannelUseCase: EndVerificationChannelUseCase
) : ViewModel(), ContainerHost<ConfirmationCodeState, ConfirmationEffect>,
    ConfirmationCodeInteractor {

    override val container: Container<ConfirmationCodeState, ConfirmationEffect> = container(
        ConfirmationCodeState(phoneNumber = UserData.phone)
    )

    init {
        startTimer()
    }

    override fun onBack() = intent { postSideEffect(ConfirmationEffect.Back) }

    override fun onResend() {
        intent {
            withContext(Dispatchers.IO) {
                startVerificationChannelUseCase.start(state.phoneNumber)
            }
        }
        Log.d("INTENT", "startTimer before intent ${Thread.currentThread().name}")
        startTimer()
        Log.d("INTENT", "startTimer after intent ${Thread.currentThread().name}")
    }

    override fun onNext() = intent {
        withContext(Dispatchers.IO) {
            if (endVerificationChannelUseCase.end(state.phoneNumber, state.code)) {
                UserData.phone = state.phoneNumber
                postSideEffect(ConfirmationEffect.Next)
            }
        }
    }

    override fun onCode(code: String) = intent {
        if (code.length <= 4) reduce { state.copy(code = code) }
    }

    private fun startTimer() = intent {
        Log.d("INTENT", "startTimer before withContext ${Thread.currentThread().name}")
        withContext(Dispatchers.Default) {
            Log.d("INTENT", "startTimer in withContext ${Thread.currentThread().name}")
            reduce { state.copy(isTimer = true) }
            for (i in 45 downTo 1) {
                delay(1000)
                if (i >= 10) reduce { state.copy(timer = "00:$i") }
                else reduce { state.copy(timer = "00:0$i") }
            }
            reduce { state.copy(isTimer = false) }
            Log.d("INTENT", "startTimer at end withContext ${Thread.currentThread().name}")
        }
        Log.d("INTENT", "startTimer after withContext ${Thread.currentThread().name}")
    }
}