package com.progressterra.ipbandroidview.ui.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.component.StoreCardComponent
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun FavoritesScreen(
    state: FavoritesState, interactor: FavoritesInteractor
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.favorites))
    }) { _, _ ->
        StateBox(state = state.screenState, refresh = { interactor.refresh() }) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(state.items) { goods ->
                    StoreCardComponent(
                        state = goods, interactor = interactor
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    IpbTheme {}
}