package com.progressterra.ipbandroidview.pages.wantthisrequests

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.wantthiscard.WantThisCardEvent
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent

class WantThisRequestsViewModel(
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
    private val wantThisRequestsUseCase: WantThisRequestsUseCase
) : BaseViewModel<WantThisRequestsState, WantThisRequestsEvent>(),
    UseWantThisRequests {

    override val initialState = WantThisRequestsState()

    fun refresh() {
        onBackground {
            emitState { it.copy(screen = ScreenState.LOADING) }
            wantThisRequestsUseCase().onSuccess { nonCached ->
                emitState { it.copy(screen = ScreenState.SUCCESS, items = cachePaging(nonCached)) }
            }.onFailure {
                emitState { it.copy(screen = ScreenState.ERROR) }
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(WantThisRequestsEvent.Back)
    }

    override fun handle(event: WantThisCardEvent) {
        postEffect(WantThisRequestsEvent.GoodsDetails(event.id))
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

    override fun handle(event: StateBoxEvent) {
        refresh()
    }
}