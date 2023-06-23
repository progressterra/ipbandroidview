package com.progressterra.ipbandroidview.pages.orderstatus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.progressterra.ipbandroidview.features.orderid.OrderId
import com.progressterra.ipbandroidview.features.orderoverview.OrderOverview
import com.progressterra.ipbandroidview.features.orderoverview.OrderOverviewState
import com.progressterra.ipbandroidview.features.ordersteps.OrderSteps
import com.progressterra.ipbandroidview.features.ordersteps.OrderStepsState
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button

@Composable
fun OrderStatusScreen(
    modifier: Modifier = Modifier,
    state: OrderStatusState,
    useComponent: UseOrderStatus
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
        state = OrderStatusState(
            orderOverview = OrderOverviewState(
                quantity = 1,
                goodsImages = listOf("")
            )
        ),
        useComponent = UseOrderStatus.Empty()
    )
}