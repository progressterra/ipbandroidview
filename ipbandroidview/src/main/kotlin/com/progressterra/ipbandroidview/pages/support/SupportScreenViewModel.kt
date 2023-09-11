package com.progressterra.ipbandroidview.pages.support

import com.progressterra.ipbandroidview.features.supportchat.SupportChatEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

class SupportScreenViewModel(
    private val fetchChatsUseCase: FetchChatsUseCase,
    private val fetchMessagesUseCase: FetchMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase
) : AbstractNonInputViewModel<SupportScreenState, SupportScreenEffect>(), UseSupportScreen {

    override fun createInitialState() = SupportScreenState()

    override fun refresh() {
        onBackground {
            emitState { createInitialState() }
            fetchChatsUseCase().onSuccess { newState ->
                val cached = newState.copy(subCategories = cachePaging(newState.subCategories))
                emitState {
                    it.copy(
                        screen = it.screen.copy(state = ScreenState.SUCCESS),
                        current = cached
                    )
                }
            }.onFailure {
                emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        if (currentState.trace.isNotEmpty()) {
            emitState { it.copy(current = it.trace.last()) }
            emitState { it.copy(trace = it.trace.dropLast(1)) }
        } else {
            postEffect(SupportScreenEffect)
        }
    }

    override fun handle(event: SupportChatEvent) {
        onBackground {
            emitState { it.copy(trace = it.trace + it.current, current = event.state) }
            if (currentState.current.finite) {
                fetchMessagesUseCase(currentState.current.id).onSuccess { newMessages ->
                    emitState { it.copy(messages = it.messages.copy(items = cachePaging(newMessages))) }
                }
            }
        }
    }

    override fun handle(event: StateColumnEvent) {
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