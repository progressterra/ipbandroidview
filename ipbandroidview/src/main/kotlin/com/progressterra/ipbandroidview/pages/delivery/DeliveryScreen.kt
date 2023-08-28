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
import com.progressterra.ipbandroidview.features.ordersteps.OrderSteps
import com.progressterra.ipbandroidview.features.ordersteps.OrderStepsState
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField
import com.progressterra.ipbandroidview.widgets.deliverypicker.DeliveryPicker

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
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                state = state.confirm,
                useComponent = useComponent,
                title = stringResource(R.string.next)
            )
        }
    }) { _, _ ->
        StateColumn(
            state = state.screen,
            scrollable = true,
            useComponent = useComponent,
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            OrderSteps(
                modifier = Modifier.padding(top = 40.dp),
                state = OrderStepsState.DELIVERY
            )
            DeliveryPicker(
                useComponent = useComponent, state = state.deliveryPicker
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                state = state.commentary,
                useComponent = useComponent,
                hint = stringResource(R.string.comment)
            )
        }
    }
}

@Preview
@Composable
private fun DeliveryScreenPreview() {
    DeliveryScreen(
        state = DeliveryState(),
        useComponent = UseDelivery.Empty()
    )
}