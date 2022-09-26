package com.progressterra.ipbandroidview.splash

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class SplashViewModel : ViewModel(), ContainerHost<SplashState, SplashEffect> {

    override val container: Container<SplashState, SplashEffect> = container(SplashState())
}