package com.progressterra.ipbandroidview.ui.orders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.OrdersGoodsLine
import com.progressterra.ipbandroidview.shared.ui.StateBox
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar

@Composable
fun OrdersScreen(
    state: OrdersState,
    interactor: OrdersInteractor
) {
    ThemedLayout(
        topBar = {
            ThemedTopAppBar(
                title = stringResource(R.string.your_orders),
                onBack = { interactor.onBack() }
            )
        }
    ) { _, _ ->
        StateBox(
            state = state.screenState,
            refresh = { interactor.refresh() }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(state.orders) {details ->
                    OrdersGoodsLine(
                        state = details,
                        openGoodsDetails = { interactor.openGoodsDetails(it) }
                    )
                }
            }
        }
    }
}