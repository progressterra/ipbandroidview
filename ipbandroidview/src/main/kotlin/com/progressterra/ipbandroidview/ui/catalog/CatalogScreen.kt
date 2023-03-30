package com.progressterra.ipbandroidview.ui.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.composable.component.CatalogBarComponent
import com.progressterra.ipbandroidview.composable.component.Category
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun CatalogScreen(
    state: CatalogState,
    interactor: CatalogInteractor
) {
    ThemedLayout(topBar = {
        CatalogBarComponent(
            state = state.catalogBarState,
            id = "main",
            useComponent = interactor
        )
    }) { _, _ ->
        StateBox(
            state = state.screenState, refresh = { interactor.refresh() }
        ) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(state.categories) { category ->
                    Category(
                        state = category,
                        openCategory = { interactor.openCategory(it) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CatalogScreenPreview() {
    IpbTheme {

    }
}