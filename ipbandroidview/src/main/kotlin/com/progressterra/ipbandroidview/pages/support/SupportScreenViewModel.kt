package com.progressterra.ipbandroidview.pages.support

import com.progressterra.ipbandroidview.features.supportchat.SupportChatEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

class SupportScreenViewModel(
    private val fetchChatsUseCase: FetchChatsUseCase,
    private val fetchMessagesUseCase: FetchMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase
) : BaseViewModel<SupportScreenState, SupportScreenEvent>(), UseSupportScreen {

    override fun createInitialState() = SupportScreenState()

    fun refresh() {
        onBackground {
            emitState { it.copy(screenState = ScreenState.LOADING) }
            fetchChatsUseCase().onSuccess { newState ->
                emitState { it.copy(screenState = ScreenState.SUCCESS, current = newState) }
            }.onFailure {
                emitState { it.copy(screenState = ScreenState.ERROR) }
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        if (currentState.trace.isNotEmpty()) {
            emitState { it.copy(current = it.trace.last()) }
            emitState { it.copy(trace = it.trace.dropLast(1)) }
        } else {
            postEffect(SupportScreenEvent)
        }
    }

    override fun handle(event: SupportChatEvent) {
        onBackground {
            emitState { it.copy(trace = it.trace + it.current, current = event.state) }
            if (currentState.current.finite) {
                fetchMessagesUseCase(currentState.current.id).onSuccess { newMessages ->
                    emitState { it.copy(messages = it.messages.copy(items = newMessages)) }
                }
            }
        }
    }

    override fun handle(event: StateBoxEvent) {
        refresh()
    }

    override fun handle(event: TextFieldEvent) {
        when (event) {
            is TextFieldEvent.TextChanged -> emitState { it.copy(input = it.input.copy(text = event.text)) }
            else -> onBackground {
                sendMessageUseCase(
                    currentState.current.id,
                    currentState.input.text
                ).onSuccess {
                    emitState { it.copy(input = it.input.copy(text = "")) }
                    fetchMessagesUseCase(currentState.current.id).onSuccess { newMessages ->
                        emitState { it.copy(messages = it.messages.copy(items = newMessages)) }
                    }
                }
            }
        }
    }
}