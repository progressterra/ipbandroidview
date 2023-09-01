package com.progressterra.ipbandroidview.pages.orderstatus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.ordernumber.OrderNumber
import com.progressterra.ipbandroidview.features.ordernumber.OrderNumberState
import com.progressterra.ipbandroidview.features.ordersteps.OrderSteps
import com.progressterra.ipbandroidview.features.ordersteps.OrderStepsState
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button

@Composable
fun OrderStatusScreen(
    modifier: Modifier = Modifier,
    state: OrderStatusScreenState,
    useComponent: UseOrderStatusScreen
) {
    ThemedLayout(
        modifier = modifier,
        topBar = {
            TopBar(
                title = stringResource(R.string.order_processing),
                showBackButton = true,
                useComponent = useComponent
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(IpbTheme.colors.surface.asBrush())
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    state = state.main,
                    useComponent = useComponent,
                    title = stringResource(R.string.to_main)
                )
            }
        }
    ) { _, _ ->
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            OrderSteps(modifier = Modifier.padding(top = 40.dp), state = OrderStepsState.FINISHED)
            OrderNumber(
                modifier = Modifier.padding(top = 40.dp, start = 20.dp, end = 20.dp),
                state = state.number,
                useComponent = useComponent
            )
        }
    }
}

@Preview
@Composable
private fun OrderStatusScreenPreview() {
    OrderStatusScreen(
        state = OrderStatusScreenState(
            number = OrderNumberState(
                quantity = 1,
                address = "lalalala",
                number = "1234"
            )
        ),
        useComponent = UseOrderStatusScreen.Empty()
    )
}