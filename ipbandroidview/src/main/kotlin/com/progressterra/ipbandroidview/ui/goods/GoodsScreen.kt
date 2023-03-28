package com.progressterra.ipbandroidview.ui.goods

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.progressterra.ipbandroidview.shared.ui.StateBox
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.composable.component.GoodsSearchBar
import com.progressterra.ipbandroidview.composable.component.StoreCardComponent
import com.progressterra.ipbandroidview.composable.component.StoreCardComponentState
import com.progressterra.ipbandroidview.composable.utils.items
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun GoodsScreen(
    state: GoodsState, interactor: GoodsInteractor
) {
    ThemedLayout(topBar = {
        GoodsSearchBar(
            state = state.goodsBarComponentState, id = "main", useComponent = interactor
        )
    }) { _, _ ->
        StateBox(state = state.screenState, refresh = { interactor.refresh() }) {
            val lazyItems: LazyPagingItems<StoreCardComponentState> =
                state.itemsFlow.collectAsLazyPagingItems()
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                if (lazyItems.itemCount > 0) items(lazyItems) { goods ->
                    goods.let {
                        StoreCardComponent(
                            state = goods, interactor = interactor
                        )

                    }
                }
                if (state.items.isNotEmpty()) items(state.items) { goods ->
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
    IpbTheme {

    }
}