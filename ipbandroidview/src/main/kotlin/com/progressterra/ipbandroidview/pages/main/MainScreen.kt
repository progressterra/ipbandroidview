package com.progressterra.ipbandroidview.pages.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.features.bonuses.Bonuses
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.widgets.galleries.Galleries

@Composable
fun MainScreen(
    modifier: Modifier = Modifier, state: MainScreenState, useComponent: UseMainScreen
) {
    ThemedLayout(
        modifier = modifier,
    ) { _, _ ->
        LazyColumn(
            contentPadding = PaddingValues(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            item {
                Bonuses(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    state = state.bonuses, useComponent = useComponent
                )
            }
            items(state.recommended) {
                Galleries(
                    state = it, useComponent = useComponent
                )
            }
        }
    }
}