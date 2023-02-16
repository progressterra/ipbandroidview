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
import com.progressterra.ipbandroidview.composable.Gallery
import com.progressterra.ipbandroidview.composable.GoodsBottomBar
import com.progressterra.ipbandroidview.composable.GoodsDetails
import com.progressterra.ipbandroidview.composable.GoodsTopAppBar
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun GoodsDetailsScreen(
    state: GoodsDetailsScreenState, interactor: GoodsDetailsInteractor
) {
    ThemedLayout(topBar = {
        GoodsTopAppBar(
            onBack = { interactor.onBack() },
            onFavorite = { interactor.favorite() },
            state = state.goodsDetails
        )
    }, bottomBar = {
        GoodsBottomBar(
            modifier = Modifier.animateContentSize(
                animationSpec = tween(
                    durationMillis = 500, easing = LinearEasing
                )
            ),
            state = state.goodsDetails,
            onAdd = { interactor.add() },
            onRemove = { interactor.remove() },
            screenState = state.screenState
        )
    }) { _, _ ->
        StateBox(state = state.screenState, refresh = { interactor.refresh() }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = AppTheme.dimensions.small)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
            ) {
                Gallery(
                    state = state.goodsDetails
                )
                GoodsDetails(
                    modifier = Modifier, state = state.goodsDetails
                )
            }
        }
    }
}

@Preview
@Composable
private fun GoodsScreenPreview() {
    AppTheme {}
}