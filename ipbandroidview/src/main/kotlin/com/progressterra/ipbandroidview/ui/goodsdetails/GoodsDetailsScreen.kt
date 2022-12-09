package com.progressterra.ipbandroidview.ui.goodsdetails

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.composable.ColorsLine
import com.progressterra.ipbandroidview.composable.Gallery
import com.progressterra.ipbandroidview.composable.GoodsBottomBar
import com.progressterra.ipbandroidview.composable.GoodsDetails
import com.progressterra.ipbandroidview.composable.GoodsTopAppBar
import com.progressterra.ipbandroidview.composable.SizesLine
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.model.store.GoodsColor
import com.progressterra.ipbandroidview.model.store.GoodsSize
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun GoodsDetailsScreen(
    state: () -> GoodsDetailsScreenState,
    refresh: () -> Unit,
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
            modifier = Modifier.animateContentSize(
                animationSpec = tween(
                    durationMillis = 500, easing = LinearEasing
                )
            ),
            state = state()::goodsDetails,
            onAdd = add,
            onRemove = remove,
            screenState = state()::screenState
        )
    }) { _, _ ->
        StateBox(
            state = state()::screenState,
            refresh = refresh
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = AppTheme.dimensions.small)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
            ) {
                Gallery(
                    state = state()::goodsDetails
                )
                ColorsLine(
                    modifier = Modifier.padding(horizontal = AppTheme.dimensions.small),
                    state = state()::goodsDetails,
                    chooseColor = color
                )
                SizesLine(
                    modifier = Modifier.padding(horizontal = AppTheme.dimensions.small),
                    state = state()::goodsDetails,
                    onSize = size,
                    onTable = sizeTable
                )
                GoodsDetails(
                    modifier = Modifier, state = state()::goodsDetails
                )
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