package com.progressterra.ipbandroidview.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.features.ProshkaCartCard
import com.progressterra.ipbandroidview.features.ProshkaCartCardEvent
import com.progressterra.ipbandroidview.features.ProshkaCartCardState
import com.progressterra.ipbandroidview.features.UseProshkaCartCard
import com.progressterra.ipbandroidview.shared.ui.CounterEvent

@Immutable
data class ProshkaCartItemsState(
    val items: List<ProshkaCartCardState> = emptyList()
)

interface UseProshkaCartItems : UseProshkaCartCard {

    class Empty : UseProshkaCartItems {

        override fun handleEvent(id: String, event: CounterEvent) = Unit

        override fun handleEvent(id: String, event: ProshkaCartCardEvent) = Unit
    }
}

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