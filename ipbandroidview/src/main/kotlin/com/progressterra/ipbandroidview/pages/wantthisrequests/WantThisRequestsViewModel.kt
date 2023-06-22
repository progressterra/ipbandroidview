package com.progressterra.ipbandroidview.pages.wantthisrequests

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.wantthiscard.WantThisCardEvent
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class WantThisRequestsViewModel(
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
    private val wantThisRequestsUseCase: WantThisRequestsUseCase
) : ContainerHost<WantThisRequestsState, WantThisRequestsEvent>,
    ViewModel(), UseWantThisRequests {

    override val container =
        container<WantThisRequestsState, WantThisRequestsEvent>(WantThisRequestsState())

    fun refresh() {
        intent {
            reduce { state.uScreenState(ScreenState.LOADING) }
            wantThisRequestsUseCase().onSuccess {
                reduce { state.uItems(it).uScreenState(ScreenState.SUCCESS) }
            }.onFailure {
                reduce { state.uScreenState(ScreenState.ERROR) }
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        intent {
            postSideEffect(WantThisRequestsEvent.Back)
        }
    }

    override fun handle(event: WantThisCardEvent) {
        intent {
            postSideEffect(WantThisRequestsEvent.GoodsDetails(event.id))
        }
    }

    override fun handle(event: CounterEvent) {
        intent {
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