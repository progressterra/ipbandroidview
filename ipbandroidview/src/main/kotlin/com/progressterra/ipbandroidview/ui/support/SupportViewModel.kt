package com.progressterra.ipbandroidview.ui.support

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class SupportViewModel : ViewModel(), ContainerHost<SupportState, SupportEffect> {

    override val container: Container<SupportState, SupportEffect> = container(SupportState())

    fun editMessage(message: String) = intent { reduce { state.copy(message = message) } }

    fun sendMessage() = intent {

    }

    fun back() = intent { postSideEffect(SupportEffect.Back) }
}