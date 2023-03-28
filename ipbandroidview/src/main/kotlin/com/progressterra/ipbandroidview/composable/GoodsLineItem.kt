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
import com.progressterra.ipbandroidview.shared.ui.niceClickable
import com.progressterra.ipbandroidview.entities.OrderGoods
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

private val picWidth = 80.dp
private val picHeight = 96.dp

@Composable
fun GoodsLineItem(
    modifier: Modifier = Modifier,
    state: OrderGoods,
    openGoodsDetails: (String) -> Unit
) {
    Box(modifier = modifier) {
        SimpleImage(
            modifier = Modifier
                .size(picWidth, picHeight)
                .clip(IpbTheme.shapes.small)
                .niceClickable { openGoodsDetails(state.id) },
            url = state.image,
            backgroundColor = IpbTheme.colors.background
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(2.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .clip(IpbTheme.shapes.tiny)
                    .padding(4.dp)
            ) {
                Text(
                    text = "x${state.inCartCounter}",
                    color = IpbTheme.colors.primary,
                    style = IpbTheme.typography.actionBarLabels
                )
            }
        }
    }
}