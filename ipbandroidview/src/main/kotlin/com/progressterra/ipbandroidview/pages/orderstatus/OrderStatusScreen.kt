package com.progressterra.ipbandroidview.pages.orderstatus

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.mainorreceipt.MainOrReceipt
import com.progressterra.ipbandroidview.features.orderid.OrderId
import com.progressterra.ipbandroidview.features.orderoverview.OrderOverview
import com.progressterra.ipbandroidview.features.ordersteps.OrderSteps
import com.progressterra.ipbandroidview.features.ordersteps.OrderStepsState
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout

@Composable
fun OrderStatusScreen(
    state: OrderStatusState,
    useComponent: UseOrderStatus
) {
    ThemedLayout(
        topBar = {
            TopBar(
                title = stringResource(R.string.order_processing),
                showBackButton = true,
                useComponent = useComponent
            )
        },
        bottomBar = {
            MainOrReceipt(state = state.mainOrReceipt, useComponent = useComponent)
        }
    ) { _, _ ->
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(40.dp))
            OrderSteps(state = OrderStepsState.FINISHED)
            Spacer(Modifier.height(40.dp))
            OrderId(state = state.orderId)
            Spacer(Modifier.height(40.dp))
            OrderOverview(state = state.orderOverview)
            Spacer(Modifier.height(40.dp))
        }
    }
}

@Preview
@Composable
private fun OrderStatusScreenPreview() {
    OrderStatusScreen(
        state = OrderStatusState(),
        useComponent = UseOrderStatus.Empty()
    )
}