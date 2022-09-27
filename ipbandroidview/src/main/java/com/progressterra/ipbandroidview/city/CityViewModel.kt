package com.progressterra.ipbandroidview.city

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

class CityViewModel : ViewModel(), ContainerHost<CityState, CityEffect>, CityInteractor {

    override val container: Container<CityState, CityEffect> = container(CityState())

    override fun onBack() = intent{
        postSideEffect(CityEffect.Back)
    }

    override fun onSkip() = intent{
        postSideEffect(CityEffect.Skip)
    }

    override fun onNext() = intent{
        postSideEffect(CityEffect.Next)
    }

    override fun onAddress(address: String) {
        TODO("Not yet implemented")
    }

    override fun onAddressFocusChanged(isFocused: Boolean) {
        TODO("Not yet implemented")
    }
}