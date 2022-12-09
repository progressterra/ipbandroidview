package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.model.store.OrderGoods
import com.progressterra.ipbandroidview.theme.AppTheme

private val picWidth = 80.dp
private val picHeight = 96.dp

@Composable
fun GoodsLineItem(
    modifier: Modifier = Modifier,
    state: () -> OrderGoods,
    openGoodsDetails: (String) -> Unit
) {
    Box(modifier = modifier) {
        SimpleImage(
            modifier = Modifier
                .size(picWidth, picHeight)
                .clip(AppTheme.shapes.small)
                .niceClickable(onClick = { openGoodsDetails(state().id) }),
            url = state()::image,
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
                    text = "x${state().inCartCounter}",
                    color = AppTheme.colors.primary,
                    style = AppTheme.typography.actionBarLabels
                )
            }
        }
    }
}