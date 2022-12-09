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
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun OrdersScreen(
    state: () -> OrdersState,
    onBack: () -> Unit,
    openGoodsDetails: (String) -> Unit,
    refresh: () -> Unit
) {
    ThemedLayout(
        topBar = {
            ThemedTopAppBar(title = stringResource(R.string.your_orders), onBack = onBack)
        }
    ) { _, _ ->
        StateBox(
            state = state()::screenState,
            refresh = refresh
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small),
                contentPadding = PaddingValues(AppTheme.dimensions.small)
            ) {
                items(state().orders) {
                    OrdersGoodsLine(
                        state = { it },
                        openGoodsDetails = openGoodsDetails
                    )
                }
            }
        }
    }
}