package com.progressterra.ipbandroidview.ui.support

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.AppSettings.ID_ENTERPRISE
import com.progressterra.ipbandroidview.domain.usecase.chat.FetchChatUseCase
import com.progressterra.ipbandroidview.domain.usecase.chat.SendMessageUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class SupportViewModel(
    private val fetchChatUseCase: FetchChatUseCase,
    private val sendMessageUseCase: SendMessageUseCase
) : ViewModel(), ContainerHost<SupportState, SupportEffect> {

    override val container: Container<SupportState, SupportEffect> = container(SupportState())

    init {
        refresh()
    }

    fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        fetchChatUseCase().onSuccess {
            reduce { state.copy(messages = it, screenState = ScreenState.SUCCESS) }
        }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
    }

    fun editMessage(message: String) = intent { reduce { state.copy(message = message) } }

    fun sendMessage() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        sendMessageUseCase(ID_ENTERPRISE, state.message).onSuccess {
            reduce { state.copy(messages = it, screenState = ScreenState.SUCCESS, message = "") }
        }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
    }

    fun back() = intent { postSideEffect(SupportEffect.Back) }
}