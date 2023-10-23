package com.progressterra.ipbandroidview.pages.chats

import com.progressterra.ipbandroidview.processes.FetchDatingChatsUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel

class ChatsScreenViewModel(
    private val datingChatsUseCase: FetchDatingChatsUseCase
) : AbstractNonInputViewModel<ChatsScreenState, ChatsScreenEffect>(), UseChatsScreen {

    init {
        onBackground {
            datingChatsUseCase.resultFlow.collect { result ->
                result.onSuccess { newFlow ->
                    emitState { it.copy(items = newFlow) }
                }
            }
        }
    }

    override fun createInitialState() = ChatsScreenState()

    override fun refresh() {
        onBackground {
            datingChatsUseCase()
        }
    }

    override fun handle(event: ChatsScreenEvent) {
        postEffect(ChatsScreenEffect.OnChat(event.id))
    }
}