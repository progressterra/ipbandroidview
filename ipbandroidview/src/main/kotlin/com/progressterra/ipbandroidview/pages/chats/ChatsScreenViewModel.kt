package com.progressterra.ipbandroidview.pages.chats

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.FetchDatingChatsUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import kotlinx.coroutines.flow.collectLatest

class ChatsScreenViewModel(
    private val datingChatsUseCase: FetchDatingChatsUseCase
) : AbstractNonInputViewModel<ChatsScreenState, ChatsScreenEffect>(), UseChatsScreen {

    init {
        onBackground {
            datingChatsUseCase.resultFlow.collectLatest { result ->
                result.onSuccess { newFlow ->
                    emitState { it.copy(items = newFlow) }
                }
            }
        }
    }

    override fun handle(event: TopBarEvent) = Unit

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