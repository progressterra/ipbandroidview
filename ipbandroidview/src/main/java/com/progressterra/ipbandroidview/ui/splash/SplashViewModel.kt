package com.progressterra.ipbandroidview.ui.splash

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidapi.user.UserData
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

class SplashViewModel : ViewModel(), ContainerHost<SplashState, SplashEffect> {

    override val container: Container<SplashState, SplashEffect> = container(SplashState())

    init {
        splashInit()
    }

    private fun splashInit() = intent {
        delay(1000)
        postSideEffect(if (!UserData.clientExist) SplashEffect.Auth else SplashEffect.NonAuth)
    }
}