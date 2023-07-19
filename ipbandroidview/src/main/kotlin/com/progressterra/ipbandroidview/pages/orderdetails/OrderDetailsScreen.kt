package com.progressterra.ipbandroidview.pages.orderdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetails
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox

@Composable
fun OrderDetailsScreen(
    modifier: Modifier = Modifier,
    state: OrderDetailsScreenState,
    useComponent: UseOrderDetailsScreen
) {
    ThemedLayout(modifier = modifier, topBar = {
        TopBar(
            title = stringResource(R.string.order),
            showBackButton = true,
            useComponent = useComponent
        )
    }) { _, _ ->
        StateBox(state = state.screenState, useComponent = useComponent) {
            Column(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp)
            ) {
                OrderDetails(
                    state = state.details,
                    useComponent = useComponent
                )
            }
        }
    }
}
