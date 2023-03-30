package com.progressterra.ipbandroidview.ui.goodsdetails

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
import com.progressterra.ipbandroidview.composable.GoodsDetails
import com.progressterra.ipbandroidview.composable.GoodsTopAppBar
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun GoodsDetailsScreen(
    state: GoodsDetailsScreenState, interactor: GoodsDetailsInteractor
) {
    ThemedLayout(topBar = {
        GoodsTopAppBar(
            onBack = { interactor.onBack() },
            onFavorite = { interactor.favorite() },
            state = state.goodsItem
        )
    }, bottomBar = {
//        GoodsBottomBar(
//            modifier = Modifier.animateContentSize(
//                animationSpec = tween(
//                    durationMillis = 500, easing = LinearEasing
//                )
//            ),
//            state = state.goodsDetails,
//            onAdd = { interactor.add() },
//            onRemove = { interactor.remove() },
//            screenState = state.screenState
//        )
    }) { _, _ ->
        StateBox(state = state.screenState, refresh = { interactor.refresh() }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 8.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Gallery(
                    state = state.goodsItem
                )
                GoodsDetails(
                    modifier = Modifier, state = state.goodsItem
                )
            }
        }
    }
}

@Preview
@Composable
private fun GoodsScreenPreview() {
    IpbTheme {}
}