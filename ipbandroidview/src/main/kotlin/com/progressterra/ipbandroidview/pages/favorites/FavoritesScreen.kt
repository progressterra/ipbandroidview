package com.progressterra.ipbandroidview.pages.favorites

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumn
import com.progressterra.ipbandroidview.widgets.storeitems.StoreItems

@Composable
fun FavoritesScreen(
    state: FavoritesState, useComponent: UseFavorites
) {
    ThemedLayout(topBar = {
        TopBar(
            title = stringResource(id = R.string.favorites),
            useComponent = useComponent,
            showBackButton = true
        )
    }) { _, _ ->
        StateColumn(
            state = state.screen,
            useComponent = useComponent
        ) {
            StoreItems(
                state = state.items,
                useComponent = useComponent
            )
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    IpbTheme {}
}