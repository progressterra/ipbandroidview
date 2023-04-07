package com.progressterra.ipbandroidview.pages.favorites

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.proshkatopbar.ProshkaTopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox
import com.progressterra.ipbandroidview.widgets.proshkastoreitems.ProshkaStoreItems

@Composable
fun FavoritesScreen(
    state: FavoritesState, useComponent: UseFavorites
) {
    ThemedLayout(topBar = {
        ProshkaTopBar(
            title = stringResource(id = R.string.favorites),
            useComponent = useComponent
        )
    }) { _, _ ->
        StateBox(
            state = state.stateBox,
            useComponent = useComponent
        ) {
            ProshkaStoreItems(
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