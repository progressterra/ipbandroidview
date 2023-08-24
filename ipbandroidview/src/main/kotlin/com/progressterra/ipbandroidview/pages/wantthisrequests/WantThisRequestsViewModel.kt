package com.progressterra.ipbandroidview.pages.wantthisrequests

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.wantthiscard.WantThisCardEvent
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.shared.mvi.BaseViewModel
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class WantThisRequestsViewModel(
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
    private val wantThisRequestsUseCase: WantThisRequestsUseCase
) : BaseViewModel<WantThisRequestsState, WantThisRequestsEvent>(),
    UseWantThisRequests {

    override fun createInitialState() = WantThisRequestsState()

    fun refresh() {
        onBackground {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            wantThisRequestsUseCase().onSuccess { nonCached ->
                emitState {
                    it.copy(
                        screen = it.screen.copy(state = ScreenState.SUCCESS),
                        items = cachePaging(nonCached)
                    )
                }
            }.onFailure {
                emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(WantThisRequestsEvent.Back)
    }

    override fun handle(event: WantThisCardEvent) {
        onBackground {
            when (event) {
                is WantThisCardEvent.Buy -> addToCartUseCase(event.id).onSuccess {
                    refresh()
                }

                is WantThisCardEvent.Open -> postEffect(WantThisRequestsEvent.RequestDetails(event.document))
            }
        }
    }

    override fun handle(event: CounterEvent) {
        onBackground {
            when (event) {
                is CounterEvent.Add -> addToCartUseCase(event.id).onSuccess {
                    refresh()
                }

                is CounterEvent.Remove -> removeFromCartUseCase(event.id).onSuccess {
                    refresh()
                }
            }
        }
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}