package com.progressterra.ipbandroidview.pages.support

import com.progressterra.ipbandroidview.features.supportchat.SupportChatEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.chat.FetchChatsUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import kotlinx.coroutines.flow.collectLatest

class SupportScreenViewModel(
    private val fetchChatsUseCase: FetchChatsUseCase
) : AbstractNonInputViewModel<SupportScreenState, SupportScreenEffect>(), UseSupportScreen {

    override fun createInitialState() = SupportScreenState()

    init {
        onBackground {
            fetchChatsUseCase.resultFlow.collectLatest { result ->
                result.onSuccess { newState ->
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
    }

    override fun refresh() {
        onBackground {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            fetchChatsUseCase()
        }
    }

    override fun handle(event: TopBarEvent) {
        if (currentState.trace.isNotEmpty()) {
            emitState { it.copy(current = it.trace.last()) }
            emitState { it.copy(trace = it.trace.dropLast(1)) }
        } else {
            postEffect(SupportScreenEffect.OnBack)
        }
    }

    override fun handle(event: SupportChatEvent) {
        onBackground {
            emitState { it.copy(trace = it.trace + it.current, current = event.state) }
            if (currentState.current.finite) {
                postEffect(SupportScreenEffect.OnNext(currentState.current.id))
            }
        }
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}