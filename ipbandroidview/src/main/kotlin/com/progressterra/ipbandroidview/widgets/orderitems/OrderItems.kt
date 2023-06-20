package com.progressterra.ipbandroidview.widgets.orderitems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.features.ordercard.OrderCard

@Composable
fun OrderItems(
    modifier: Modifier = Modifier, state: OrderItemsState, useComponent: UseOrderItems
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(state.items) { item ->
            OrderCard(
                state = item, useComponent = useComponent
            )
        }
    }
}