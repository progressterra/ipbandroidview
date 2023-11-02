package com.progressterra.ipbandroidview.pages.connections

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.dating.ConnectionsUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class ConnectionsScreenViewModel(
    private val connectionsUseCase: ConnectionsUseCase
) : AbstractNonInputViewModel<ConnectionsScreenState, ConnectionsScreenEffect>(),
    UseConnectionsScreen {

    init {
        onBackground {
            connectionsUseCase.resultFlow.collect { result ->
                result.onSuccess { newState ->
                    emitState {
                        newState.copy(
                            incoming = cachePaging(newState.incoming),
                            successIn = cachePaging(newState.successIn),
                            successOut = cachePaging(newState.successOut),
                            pending = cachePaging(newState.pending)
                        )
                    }
                }.onFailure {
                    emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
                }
            }
        }
    }

    override fun createInitialState() = ConnectionsScreenState()

    override fun refresh() {
        onBackground {
            connectionsUseCase()
        }
    }

    override fun handle(event: ConnectionsScreenEvent) {
        postEffect(ConnectionsScreenEffect.OnProfile(event.user))
    }

    override fun handle(event: TopBarEvent) = Unit

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}