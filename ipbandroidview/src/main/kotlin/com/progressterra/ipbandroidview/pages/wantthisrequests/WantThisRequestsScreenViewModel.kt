package com.progressterra.ipbandroidview.pages.wantthisrequests

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.wantthiscard.WantThisCardEvent
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class WantThisRequestsScreenViewModel(
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
    private val wantThisRequestsUseCase: WantThisRequestsUseCase
) : AbstractNonInputViewModel<WantThisRequestsScreenState, WantThisRequestsScreenEffect>(),
    UseWantThisRequestsScreen {

    override fun createInitialState() = WantThisRequestsScreenState()

    override fun refresh() {
        onBackground {
            emitState { createInitialState() }
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
        postEffect(WantThisRequestsScreenEffect.Back)
    }

    override fun handle(event: WantThisCardEvent) {
        onBackground {
            when (event) {
                is WantThisCardEvent.Buy -> addToCartUseCase(event.id).onSuccess {
                    refresh()
                }

                is WantThisCardEvent.Open -> postEffect(
                    WantThisRequestsScreenEffect.RequestDetails(
                        event.document
                    )
                )
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