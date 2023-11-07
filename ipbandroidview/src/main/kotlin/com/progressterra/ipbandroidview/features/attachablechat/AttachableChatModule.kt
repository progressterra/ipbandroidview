package com.progressterra.ipbandroidview.features.attachablechat

import com.progressterra.ipbandroidview.processes.FetchMessagesUseCase
import com.progressterra.ipbandroidview.processes.SendMessageUseCase
import com.progressterra.ipbandroidview.shared.mvi.ModuleUser
import com.progressterra.ipbandroidview.shared.mvi.Operations
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

class AttachableChatModule(
    private val sendMessageUseCase: SendMessageUseCase,
    private val fetchMessagesUseCase: FetchMessagesUseCase,
    operations: Operations,
    user: ModuleUser<AttachableChatState>
) : UseAttachableChat, Operations by operations, ModuleUser<AttachableChatState> by user {

    override fun handle(event: StateColumnEvent) {
        if (event.id == "chat") {
            refresh()
        }
    }

    fun setup(newId: String) {
        emitModuleState { it.copy(id = newId, isVisible = false) }
    }

    fun open() {
        emitModuleState { it.copy(isVisible = true) }
    }

    fun refresh() {
        onBackground {
            emitModuleState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            fetchMessagesUseCase(moduleState.id).onSuccess { newMessages ->
                emitModuleState {
                    it.copy(
                        messagesState = it.messagesState.copy(
                            items = cachePaging(newMessages)
                        ),
                        screen = it.screen.copy(state = ScreenState.SUCCESS)
                    )
                }
            }.onFailure {
                emitModuleState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
            }
        }
    }

    private fun sendMessage() {
        onBackground {
            emitModuleState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            sendMessageUseCase(
                moduleState.id,
                moduleState.input.text
            ).onSuccess {
                emitModuleState {
                    it.copy(
                        input = it.input.copy(text = ""),
                        screen = it.screen.copy(state = ScreenState.SUCCESS)
                    )
                }
                refresh()
            }.onFailure {
                emitModuleState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
            }
        }
    }

    override fun handle(event: AttachableChatEvent) {
        emitModuleState { it.copy(isVisible = false) }
    }

    override fun handle(event: TextFieldEvent) {
        if (event.id == "input") {
            when (event) {
                is TextFieldEvent.TextChanged -> emitModuleState {
                    it.copy(input = it.input.copy(text = event.text))
                }

                else -> sendMessage()
            }
        }
    }
}