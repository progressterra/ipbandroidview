package com.progressterra.ipbandroidview.ui.support

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.AppSettings.ID_ENTERPRISE
import com.progressterra.ipbandroidview.domain.usecase.chat.FetchChatUseCase
import com.progressterra.ipbandroidview.domain.usecase.chat.SendMessageUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class SupportViewModel(
    private val fetchChatUseCase: FetchChatUseCase,
    private val sendMessageUseCase: SendMessageUseCase
) : ViewModel(), ContainerHost<SupportState, SupportEffect>, SupportScreenInteractor {

    override val container: Container<SupportState, SupportEffect> = container(SupportState())

    init {
        refresh()
    }

    override fun refresh() = intent {
        fetchChatUseCase().onSuccess {
            reduce { state.copy(messages = it, screenState = ScreenState.SUCCESS) }
        }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
    }

    override fun editMessage(message: String) = blockingIntent { reduce { state.copy(message = message) } }

    override fun sendMessage() = intent {
        sendMessageUseCase(ID_ENTERPRISE, state.message).onSuccess {
            reduce { state.copy(messages = it, screenState = ScreenState.SUCCESS, message = "") }
        }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
    }

    override fun onBack() = intent { postSideEffect(SupportEffect.Back) }
}