package com.progressterra.ipbandroidview.pages.delivery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.Delivery
import com.progressterra.ipbandroidview.entities.PickUpPointInfo
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.features.ordersteps.OrderSteps
import com.progressterra.ipbandroidview.features.ordersteps.OrderStepsState
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxState
import com.progressterra.ipbandroidview.widgets.deliverypicker.DeliveryPicker
import com.progressterra.ipbandroidview.widgets.deliverypicker.DeliveryPickerState

@Composable
fun DeliveryScreen(
    state: DeliveryState, useComponent: UseDelivery
) {
    ThemedLayout(topBar = {
        TopBar(
            title = stringResource(R.string.processing),
            useComponent = useComponent,
            showBackButton = true
        )
    }, bottomBar = {
        Column(
            modifier = Modifier.padding(vertical = 40.dp, horizontal = 20.dp)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                state = state.confirm,
                useComponent = useComponent,
                title = stringResource(R.string.next)
            )
        }
    }) { _, _ ->
        StateBox(
            state = state.stateBox, useComponent = useComponent
        ) {
            Column(
                modifier = Modifier.padding(top = 40.dp),
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                OrderSteps(
                    state = OrderStepsState.DELIVERY
                )
                DeliveryPicker(
                    useComponent = useComponent, state = state.deliveryPicker
                )
            }
        }
    }
}

@Preview
@Composable
private fun DeliveryScreenPreview() {
    val m = Delivery.CourierDelivery(
        price = SimplePrice(price = 6.7), type = "fames"
    )
    DeliveryScreen(
        state = DeliveryState(
            stateBox = StateBoxState(ScreenState.SUCCESS), deliveryPicker = DeliveryPickerState(
                selectedDeliveryMethod = m,
                deliveryMethods = listOf(
                    Delivery.PickUpPointDelivery(
                        points = listOf(), currentPoint = PickUpPointInfo(
                            price = SimplePrice(price = 0.1),
                            address = "sociis",
                            workHour = "accusata",
                            latitude = 2.3,
                            type = "cam",
                            longitude = 4.5,
                            pickupPointCode = "efficiantur",
                            path = "phasellus"
                        )
                    ), m
                ),
            )
        ), useComponent = UseDelivery.Empty()
    )
}

@Preview
@Composable
private fun DeliveryScreenPreview2() {
    val m = Delivery.PickUpPointDelivery(
        points = listOf(), currentPoint = PickUpPointInfo(
            price = SimplePrice(price = 0.1),
            address = "sociis",
            workHour = "accusata",
            latitude = 2.3,
            type = "cam",
            longitude = 4.5,
            pickupPointCode = "efficiantur",
            path = "phasellus"
        )
    )
    DeliveryScreen(
        state = DeliveryState(
            stateBox = StateBoxState(ScreenState.SUCCESS), deliveryPicker = DeliveryPickerState(
                selectedDeliveryMethod = m,
                deliveryMethods = listOf(
                    m, Delivery.CourierDelivery(
                        price = SimplePrice(price = 6.7), type = "fames"
                    )
                ),
            )
        ), useComponent = UseDelivery.Empty()
    )
}