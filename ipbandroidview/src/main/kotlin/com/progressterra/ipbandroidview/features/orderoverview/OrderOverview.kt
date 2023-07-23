package com.progressterra.ipbandroidview.features.orderoverview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.SimpleImage

@Composable
fun OrderOverview(
    modifier: Modifier = Modifier, state: OrderOverviewState
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(128.dp)) {
            if (state.goodsImages.size >= 2) {
                state.goodsImages.forEachIndexed { index, image ->
                    val horizontalPadding = 38.dp / (state.goodsImages.size - 1) * index
                    val verticalPadding = 34.dp / (state.goodsImages.size - 1) * index
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(start = horizontalPadding, top = verticalPadding)
                    ) {
                        SimpleImage(
                            modifier = Modifier
                                .size(90.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            image = image,
                            backgroundColor = IpbTheme.colors.background.asColor()
                        )
                    }
                }
            } else if (state.goodsImages.size == 1) {
                SimpleImage(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .align(Alignment.Center),
                    image = state.goodsImages.firstOrNull() ?: "",
                    backgroundColor = IpbTheme.colors.background.asColor()
                )
            }
        }
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            BrushedText(
                text = "${state.quantity} ${stringResource(R.string.items_paid)}",
                style = IpbTheme.typography.subHeadlineBold,
                tint = IpbTheme.colors.onBackground.asBrush()
            )
            BrushedText(
                text = stringResource(R.string.delivery_address),
                style = IpbTheme.typography.footnoteBold,
                tint = IpbTheme.colors.textTertiary.asBrush()
            )
            BrushedText(
                text = state.address,
                style = IpbTheme.typography.footnoteRegular,
                tint = IpbTheme.colors.textTertiary.asBrush()
            )
        }
    }
}

@Preview
@Composable
private fun OrderOverviewPreview() {
    IpbTheme {
        OrderOverview(
            state = OrderOverviewState(
                quantity = 3, address = "Karl-Marx-Allee 1, 10178 Berlin", goodsImages = listOf(
                    "https://images.unsplash.com/photo-1616489953143-8b8b2b2b2b1c?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
                    "https://images.unsplash.com/photo-1616489953143-8b8b2b2b2b1c?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
                    "https://images.unsplash.com/photo-1616489953143-8b8b2b2b2b1c?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80"
                )
            )
        )
    }
}