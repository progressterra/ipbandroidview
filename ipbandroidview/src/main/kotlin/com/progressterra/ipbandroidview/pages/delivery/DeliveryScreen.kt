package com.progressterra.ipbandroidview.pages.delivery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.ordersteps.OrderSteps
import com.progressterra.ipbandroidview.features.ordersteps.OrderStepsState
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField
import com.progressterra.ipbandroidview.widgets.deliverypicker.DeliveryPicker

@Composable
fun DeliveryScreen(
    modifier: Modifier = Modifier,
    state: DeliveryScreenState, useComponent: UseDelivery
) {
    ThemedLayout(
        modifier = modifier,
        topBar = {
            TopBar(
                title = stringResource(R.string.processing),
                useComponent = useComponent,
                showBackButton = true
            )
        }, bottomBar = {
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(IpbTheme.colors.surface.asBrush())
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 36.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    state = state.confirm,
                    useComponent = useComponent,
                    title = stringResource(R.string.next)
                )
            }
        }) { _, _ ->
        val scrollState = rememberScrollState()
        StateColumn(
            state = state.screen,
            scrollable = true,
            scrollState = scrollState,
            useComponent = useComponent,
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            OrderSteps(
                modifier = Modifier.padding(top = 40.dp),
                state = OrderStepsState.DELIVERY
            )
            DeliveryPicker(
                modifier = Modifier.padding(horizontal = 20.dp),
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
            LaunchedEffect(state.deliveryPicker.address.text) {
                if (scrollState.canScrollForward) {
                    scrollState.animateScrollTo(scrollState.maxValue)
                }
            }
        }
    }
}

@Preview
@Composable
private fun DeliveryScreenPreview() {
    DeliveryScreen(
        state = DeliveryScreenState(),
        useComponent = UseDelivery.Empty()
    )
}