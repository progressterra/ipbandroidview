package com.progressterra.ipbandroidview.ui.orderprocessing

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.composable.component.OrderProcessingComponentState
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class OrderProcessingViewModel : ViewModel(),
    ContainerHost<OrderProcessingComponentState, OrderProcessingEffect>, OrderProcessingInteractor {

    override val container: Container<OrderProcessingComponentState, OrderProcessingEffect> =
        container(OrderProcessingComponentState())

    fun setOrderResult(result: OrderProcessingComponentState) = intent { reduce { result } }

    override fun onNext() = intent { postSideEffect(OrderProcessingEffect.Next) }

    override fun onBack() = intent { postSideEffect(OrderProcessingEffect.Back) }
}