package com.progressterra.ipbandroidview.pages.delivery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.ordersteps.OrderSteps
import com.progressterra.ipbandroidview.features.ordersteps.OrderStepsState
import com.progressterra.ipbandroidview.features.proshkatopbar.ProshkaTopBar
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox
import com.progressterra.ipbandroidview.widgets.deliverypicker.DeliveryPicker

@Composable
fun DeliveryScreen(
    state: DeliveryState,
    useComponent: UseDelivery
) {
    ThemedLayout(
        topBar = {
            ProshkaTopBar(
                title = stringResource(R.string.processing),
                useComponent = useComponent
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .padding(vertical = 40.dp, horizontal = 20.dp)
                    .shadow(6.dp)
            ) {
                Button(
                    state = state.confirm,
                    useComponent = useComponent,
                    title = stringResource(R.string.next)
                )
            }
        }
    ) { _, _ ->
        StateBox(
            state = state.stateBoxState, useComponent = useComponent
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                OrderSteps(
                    state = OrderStepsState.DELIVERY
                )
                DeliveryPicker(
                    useComponent = useComponent,
                    state = state.deliveryPickerState
                )
            }
        }
    }
}