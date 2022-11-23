package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.element.SimpleImage
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.model.Cart
import com.progressterra.ipbandroidview.model.SimplePrice
import com.progressterra.ipbandroidview.theme.AppTheme

private val picWidth = 80.dp
private val picHeight = 96.dp

@Composable
fun GoodsLine(
    modifier: Modifier = Modifier,
    state: () -> Cart,
    openGoodsDetails: (String) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .padding(AppTheme.dimensions.medium),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
    ) {
        Text(
            text = stringResource(R.string.goods),
            color = AppTheme.colors.black,
            style = AppTheme.typography.title
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)) {
            items(state().listGoods) {
                Box {
                    SimpleImage(
                        modifier = Modifier
                            .size(picWidth, picHeight)
                            .clip(AppTheme.shapes.small)
                            .niceClickable(onClick = { openGoodsDetails(it.id) }),
                        url = it::image,
                        backgroundColor = AppTheme.colors.background
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(AppTheme.dimensions.tiniest)
                    ) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .clip(AppTheme.shapes.tiny)
                                .padding(AppTheme.dimensions.tiny)
                        ) {
                            Text(
                                text = "x${it.inCartCounter}",
                                color = AppTheme.colors.primary,
                                style = AppTheme.typography.actionBarLabels
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun GoodsLinePreview() {
    AppTheme {
        GoodsLine(state = {
            Cart()
        }, openGoodsDetails = {})
    }
}