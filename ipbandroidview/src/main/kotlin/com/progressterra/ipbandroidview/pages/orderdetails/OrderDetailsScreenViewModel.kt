package com.progressterra.ipbandroidview.pages.orderdetails

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.ordercard.OrderCardEvent
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class OrderDetailsScreenViewModel : ContainerHost<OrderDetailsScreenState, OrderDetailsScreenEvent>,
    ViewModel(), UseOrderDetailsScreen {

    override val container = container<OrderDetailsScreenState, OrderDetailsScreenEvent>(
        OrderDetailsScreenState()
    )

    override fun handle(event: OrderCardEvent) {
        TODO("Not yet implemented")
    }

    override fun handle(event: OrderDetailsEvent) {
        TODO("Not yet implemented")
    }

    override fun handle(event: TopBarEvent) {
        TODO("Not yet implemented")
    }
}