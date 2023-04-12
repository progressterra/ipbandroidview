package com.progressterra.ipbandroidview.widgets.cartitems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.features.cartcard.CartCard

@Composable
fun CartItems(
    modifier: Modifier = Modifier,
    state: CartItemsState,
    useComponent: UseCartItems
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        items(state.items) { item ->
            CartCard(
                state = item,
                useComponent = useComponent
            )
        }
    }
}