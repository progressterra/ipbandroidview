package com.progressterra.ipbandroidview.pages.ordertracking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidapi.api.cart.models.TypeStatusOrder
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.ordertracking.OrderTracking
import com.progressterra.ipbandroidview.features.ordertracking.OrderTrackingState
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout

@Composable
fun OrderTrackingScreen(
    modifier: Modifier = Modifier,
    state: OrderTrackingScreenState,
    useComponent: UseOrderTrackingScreen
) {
    ThemedLayout(
        modifier = modifier,
        topBar = {
            TopBar(
                title = stringResource(R.string.tracking),
                showBackButton = true,
                useComponent = useComponent
            )
        }
    ) { _, _ ->
        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp)
        ) {
            OrderTracking(state = state.tracking)
        }
    }
}

@Preview
@Composable
private fun OrderTrackingScreenPreview() {
    IpbTheme {
        OrderTrackingScreen(
            state = OrderTrackingScreenState(
                tracking = OrderTrackingState(
                    status = TypeStatusOrder.SENT_TO_WAREHOUSE,
                    number = "123456"
                )
            ),
            useComponent = UseOrderTrackingScreen.Empty()
        )
    }
}
