package com.progressterra.ipbandroidview.pages.chat

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.FetchMessagesUseCase
import com.progressterra.ipbandroidview.processes.SendMessageUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

class ChatScreenViewModel(
    private val fetchMessagesUseCase: FetchMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase
) : AbstractNonInputViewModel<ChatScreenState, ChatScreenEffect>(), UseChatScreen {

    override fun createInitialState() = ChatScreenState()

    override fun refresh() {
        onBackground {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            fetchMessagesUseCase(currentState.id).onSuccess { newMessages ->
                emitState {
                    it.copy(
                        messages = it.messages.copy(items = cachePaging(newMessages)),
                        screen = it.screen.copy(state = ScreenState.SUCCESS)
                    )
                }
            }.onFailure {
                emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
            }
        }
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }

    override fun handle(event: TopBarEvent) {
        postEffect(ChatScreenEffect)
    }

    override fun handle(event: TextFieldEvent) {
        when (event) {
            is TextFieldEvent.TextChanged -> emitState { it.copy(input = it.input.copy(text = event.text)) }
            else -> onBackground {
                sendMessageUseCase(
                    currentState.id,
                    currentState.input.text
                ).onSuccess {
                    emitState { it.copy(input = it.input.copy(text = "")) }
                    fetchMessagesUseCase(currentState.id).onSuccess { newMessages ->
                        emitState {
                            it.copy(
                                messages = it.messages.copy(
                                    items = cachePaging(
                                        newMessages
                                    )
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}