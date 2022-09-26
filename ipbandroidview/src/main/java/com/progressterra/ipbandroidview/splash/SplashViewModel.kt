package com.progressterra.ipbandroidview.splash

import androidx.lifecycle.ViewModel
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

    //TODO some preloading here??
    private fun splashInit() = intent {
        delay(1000)
        postSideEffect(SplashEffect.Ready)
    }
}