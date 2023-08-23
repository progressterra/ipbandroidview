package com.progressterra.ipbandroidview.pages.orderdetails

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.attachablechat.AttachableChat
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetails
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumn

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
        StateColumn(state = state.screen, useComponent = useComponent, scrollable = true) {
            OrderDetails(
                modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp),
                state = state.details,
                useComponent = useComponent
            )
            Spacer(modifier = Modifier.height(20.dp))
            AttachableChat(
                modifier = Modifier.padding(horizontal = 20.dp),
                state = state.chat,
                useComponent = useComponent,
                canBeClosed = true
            )
        }
    }
}
