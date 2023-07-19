package com.progressterra.ipbandroidview.pages.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.features.bonuses.Bonuses
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumn
import com.progressterra.ipbandroidview.widgets.galleries.Galleries

@Composable
fun MainScreen(
    state: MainState, useComponent: UseMain
) {
    ThemedLayout { _, _ ->
        StateColumn(
            state = state.stateBox, useComponent = useComponent
        ) {
            LazyColumn(
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
}