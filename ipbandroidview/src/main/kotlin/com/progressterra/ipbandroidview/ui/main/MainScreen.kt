package com.progressterra.ipbandroidview.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.components.storeitem.StoreItemCard
import com.progressterra.ipbandroidview.components.storeitem.StoreItemState
import com.progressterra.ipbandroidview.components.topbar.SearchTopBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun MainScreen(state: MainState, interactor: MainInteractor) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        SearchTopBar(state = state.searchBarState,
            onBack = { interactor.back() },
            onKeyword = { interactor.keyword(it) },
            onSearch = { interactor.search() },
            onFilters = {})
    }) {
        Column(modifier = Modifier.padding(it)) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppTheme.colors.background),
                columns = GridCells.Fixed(AppTheme.customization.catalogStyle.columns),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.items) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        StoreItemCard(modifier = Modifier.align(Alignment.Center),
                            state = it,
                            onClick = { interactor.card(it.id) },
                            onFavorite = { interactor.favorite(it.id) })
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    AppTheme {
        MainScreen(
            state = MainState(
                items = listOf(
                    StoreItemState(
                        id = "", imageUri = "", price = "3 500 P", "SOME COOL ITEM", true
                    ), StoreItemState(
                        id = "", imageUri = "", price = "3 500 P", "SOME COOL ITEM", true
                    ), StoreItemState(
                        id = "", imageUri = "", price = "3 500 P", "SOME COOL ITEM", true
                    ), StoreItemState(
                        id = "", imageUri = "", price = "3 500 P", "SOME COOL ITEM", true
                    )
                )
            ), interactor = MainInteractor.Empty()
        )
    }
}