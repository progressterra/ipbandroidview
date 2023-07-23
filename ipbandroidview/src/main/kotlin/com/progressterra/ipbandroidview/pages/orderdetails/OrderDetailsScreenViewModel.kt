package com.progressterra.ipbandroidview.pages.orderdetails

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.ordercard.OrderCardEvent
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class OrderDetailsScreenViewModel(
    private val orderDetailsUseCase: OrderDetailsUseCase
) : ContainerHost<OrderDetailsScreenState, OrderDetailsScreenEvent>,
    ViewModel(), UseOrderDetailsScreen {

    override val container = container<OrderDetailsScreenState, OrderDetailsScreenEvent>(
        OrderDetailsScreenState()
    )

    fun setupId(newId: String) {
        blockingIntent {
            reduce { state.uId(newId) }
        }
    }

    fun refresh() {
        intent {
            reduce { state.uScreenState(ScreenState.LOADING) }
            orderDetailsUseCase(state.id).onSuccess {
                reduce { state.uDetails(it).uScreenState(ScreenState.SUCCESS) }
            }.onFailure {
                reduce { state.uScreenState(ScreenState.ERROR) }
            }
        }
    }

    override fun handle(event: StateBoxEvent) {
        refresh()
    }

    override fun handle(event: OrderCardEvent) {
        intent {
            postSideEffect(OrderDetailsScreenEvent.OpenGoods(event.id))
        }
    }

    override fun handle(event: OrderDetailsEvent) {
        intent {
            postSideEffect(OrderDetailsScreenEvent.Tracking(state.details.toOrderTrackingState()))
        }
    }

    override fun handle(event: TopBarEvent) {
        intent {
            postSideEffect(OrderDetailsScreenEvent.Back)
        }
    }
}