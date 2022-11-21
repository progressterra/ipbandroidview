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
import com.progressterra.ipbandroidview.composable.component.ColorsLine
import com.progressterra.ipbandroidview.composable.component.Gallery
import com.progressterra.ipbandroidview.composable.component.GoodsBottomBar
import com.progressterra.ipbandroidview.composable.component.GoodsDetails
import com.progressterra.ipbandroidview.composable.component.GoodsTopAppBar
import com.progressterra.ipbandroidview.composable.component.SizesLine
import com.progressterra.ipbandroidview.composable.component.ThemedLayout
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
            state = state()::goodsDetails
        )
    }, bottomBar = {
        GoodsBottomBar(
            state = state()::goodsDetails,
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
                        .aspectRatio(1f), state = state()::goodsDetails
                )
            }
            item {
                ColorsLine(
                    modifier = Modifier.fillMaxWidth(),
                    state = state()::goodsDetails,
                    chooseColor = color
                )
            }
            item {
                SizesLine(
                    modifier = Modifier.fillMaxWidth(),
                    state = state()::goodsDetails,
                    onSize = size,
                    onTable = sizeTable
                )
            }
            item {
                GoodsDetails(
                    modifier = Modifier.fillMaxWidth(), state = state()::goodsDetails
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