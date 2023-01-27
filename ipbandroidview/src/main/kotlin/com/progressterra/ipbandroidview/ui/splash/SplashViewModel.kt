package com.progressterra.ipbandroidview.ui.splash

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.domain.usecase.user.UserExistsUseCase
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

class SplashViewModel(
    private val clientExistsUseCase: UserExistsUseCase
) : ViewModel(), ContainerHost<SplashState, SplashEffect> {

    override val container: Container<SplashState, SplashEffect> = container(SplashState())

    init {
        splashInit()
    }

    private fun splashInit() = intent {
        postSideEffect(if (clientExistsUseCase()) SplashEffect.OpenMain else SplashEffect.OpenAuth)
    }
}