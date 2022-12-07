package com.progressterra.ipbandroidview.ui.goodsdetails

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.progressterra.ipbandroidview.composable.element.StateBox
import com.progressterra.ipbandroidview.model.GoodsColor
import com.progressterra.ipbandroidview.model.GoodsSize
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
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
            ) {
                    Gallery(
                        modifier = Modifier
                            .aspectRatio(1f), state = state()::goodsDetails
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