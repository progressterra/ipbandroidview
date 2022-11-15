package com.progressterra.ipbandroidview.ui.goodsdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.components.ColorsLine
import com.progressterra.ipbandroidview.components.Gallery
import com.progressterra.ipbandroidview.components.SizesLine
import com.progressterra.ipbandroidview.components.ThemedLayout
import com.progressterra.ipbandroidview.components.GoodsBottomBar
import com.progressterra.ipbandroidview.components.goodsdetails.GoodsDetails
import com.progressterra.ipbandroidview.components.bar.GoodsTopAppBar
import com.progressterra.ipbandroidview.model.GoodsColor
import com.progressterra.ipbandroidview.model.GoodsSize
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun GoodsDetailsScreen(
    state: () -> GoodsDetailsScreenState,
    add: () -> Unit,
    remove: () -> Unit,
    favorite: () -> Unit,
    sizeTable: () -> Unit,
    size: (GoodsSize) -> Unit,
    back: () -> Unit,
    color: (GoodsColor) -> Unit,
) {
    ThemedLayout(topBar = {
        GoodsTopAppBar(
            onBack = back,
            onFavorite = favorite,
            state = state
        )
    }, bottomBar = {
        GoodsBottomBar(
            state = state,
            onAdd = add,
            onRemove = remove,
            screenState = state()::screenState
        )
    }) { _, _ ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small),
            contentPadding = PaddingValues(AppTheme.dimensions.small)
        ) {
            item {
                Gallery(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f), state = state
                )
            }
            item {
                ColorsLine(
                    modifier = Modifier.fillMaxWidth(),
                    state = state,
                    onColor = color
                )
            }
            item {
                SizesLine(
                    modifier = Modifier.fillMaxWidth(),
                    state = state,
                    onSize = size,
                    onTable = sizeTable
                )
            }
            item {
                GoodsDetails(
                    modifier = Modifier.fillMaxWidth(), state = state
                )
                Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
            }
        }
    }
}

@Preview
@Composable
private fun GoodsScreenPreview() {
    AppTheme {
    }
}