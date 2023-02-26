package com.progressterra.ipbandroidview.ui.support

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.ChatInputEvent
import com.progressterra.ipbandroidview.composable.ChatInputState
import com.progressterra.ipbandroidview.composable.component.TextFieldComponentEvent
import com.progressterra.ipbandroidview.composable.component.TextFieldComponentState
import com.progressterra.ipbandroidview.core.ManageResources
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
    private val sendMessageUseCase: SendMessageUseCase,
    manageResources: ManageResources
) : ViewModel(), ContainerHost<SupportState, SupportEffect>, SupportInteractor {

    override val container: Container<SupportState, SupportEffect> = container(
        SupportState(
            chatInput = ChatInputState(
                message = TextFieldComponentState(
                    hint = manageResources.string(R.string.request)
                )
            )
        )
    )

    init {
        refresh()
    }

    override fun refresh() = intent {
        reduce {
            val newChatInput = state.chatInput.updateEnabled(false)
            state.copy(screenState = ScreenState.LOADING, chatInput = newChatInput)
        }
        fetchChatUseCase().onSuccess {
            reduce {
                val newChatInput = state.chatInput.updateEnabled(true)
                state.copy(
                    messages = it, screenState = ScreenState.SUCCESS, chatInput = newChatInput
                )
            }
        }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
    }

    private fun sendMessage() = intent {
        reduce {
            val newChatInput = state.chatInput.updateEnabled(false).updateMessage("")
            state.copy(screenState = ScreenState.LOADING, chatInput = newChatInput)
        }
        sendMessageUseCase(ID_ENTERPRISE, state.chatInput.message.text).onSuccess {
            reduce {
                val newChatInput = state.chatInput.updateEnabled(true)
                state.copy(
                    messages = it, screenState = ScreenState.SUCCESS, chatInput = newChatInput
                )
            }
        }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
    }

    override fun handleEvent(id: String, event: ChatInputEvent) {
        when (id) {
            "main" -> when (event) {
                is ChatInputEvent.Send -> sendMessage()
            }
        }
    }

    override fun handleEvent(id: String, event: TextFieldComponentEvent) = blockingIntent {
        when (id) {
            "message" -> when (event) {
                is TextFieldComponentEvent.TextChanged -> reduce {
                    val newChatInput = state.chatInput.updateMessage(event.text)
                    state.copy(chatInput = newChatInput)
                }
                is TextFieldComponentEvent.Action -> sendMessage()
            }
        }
    }

    override fun onBack() = intent { postSideEffect(SupportEffect.Back) }
}