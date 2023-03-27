package com.progressterra.ipbandroidview.widgets.proshkacartitems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.features.proshkacartcard.ProshkaCartCard

@Composable
fun ProshkaCartItems(
    modifier: Modifier = Modifier,
    state: ProshkaCartItemsState,
    useComponent: UseProshkaCartItems
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        items(state.items) { item ->
            ProshkaCartCard(
                state = item,
                useComponent = useComponent
            )
        }
    }
}