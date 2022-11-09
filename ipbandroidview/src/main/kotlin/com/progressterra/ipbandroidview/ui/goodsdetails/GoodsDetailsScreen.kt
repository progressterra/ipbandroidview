package com.progressterra.ipbandroidview.ui.goodsdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.progressterra.ipbandroidview.components.ColorsLine
import com.progressterra.ipbandroidview.components.Gallery
import com.progressterra.ipbandroidview.components.SizesLine
import com.progressterra.ipbandroidview.components.bottombar.GoodsBottomBar
import com.progressterra.ipbandroidview.components.goodsdetails.GoodsDetails
import com.progressterra.ipbandroidview.components.topbar.GoodsTopAppBar
import com.progressterra.ipbandroidview.dto.Goods
import com.progressterra.ipbandroidview.dto.GoodsColor
import com.progressterra.ipbandroidview.dto.size.GoodsSize
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun GoodsDetailsScreen(state: GoodsDetailsScreenState, interactor: GoodsDetailsInteractor) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        GoodsTopAppBar(
            onBack = { interactor.back() },
            onFavorite = { interactor.favorite() },
            state = state
        )
    }, bottomBar = {
        GoodsBottomBar(state = state,
            onAdd = { interactor.add() },
            onRemove = { interactor.remove() })
    }) { padding ->
        ConstraintLayout(
            modifier = Modifier
                .background(AppTheme.colors.background)
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .padding(start = 8.dp, top = 8.dp, end = 8.dp)
        ) {
            val (gallery, colors, sizes, details) = createRefs()
            val margin = AppTheme.dimensions.medium
            Gallery(modifier = Modifier
                .constrainAs(gallery) {
                    width = Dimension.fillToConstraints
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
                .aspectRatio(1f), state = state)
            ColorsLine(modifier = Modifier.constrainAs(colors) {
                width = Dimension.fillToConstraints
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(gallery.bottom, margin)
            }, state = state, onColor = { interactor.color(it) })
            SizesLine(modifier = Modifier.constrainAs(sizes) {
                width = Dimension.fillToConstraints
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(colors.bottom, margin)
            },
                state = state,
                onSize = { interactor.size(it) },
                onTable = { interactor.sizeTable() })
            GoodsDetails(
                modifier = Modifier.constrainAs(details) {
                    width = Dimension.fillToConstraints
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(sizes.bottom, margin)
                }, state = state
            )
        }
    }
}

@Preview
@Composable
private fun GoodsScreenPreview() {
    AppTheme {
        val current = GoodsSize(
            primary = "M", secondary = "36", available = true
        )
        GoodsDetailsScreen(
            state = GoodsDetailsScreenState(
                Goods(
                    id = "",
                    name = "YOOU COOL NAME BRO",
                    description = "IT IS SUCH A NICE ITEM IT IS NIIICE YOY YOY",
                    price = "9999 USD",
                    color = GoodsColor(image = "", name = ""),
                    favorite = false,
                    image = "",
                    images = listOf("", "", ""),
                    parameters = listOf(),
                    inCartCounter = 0,
                    sizes = listOf(
                        current,
                        GoodsSize(
                            primary = "L", secondary = "38", available = true
                        ),
                        GoodsSize(
                            primary = "XL", secondary = "55", available = false
                        ),
                        GoodsSize(
                            primary = "XXL", secondary = "77", available = true
                        ),
                    ),
                    size = current,
                    colors = listOf(GoodsColor("", ""), GoodsColor("", ""), GoodsColor("", "")),
                )
            ), interactor = GoodsDetailsInteractor.Empty()
        )
    }
}