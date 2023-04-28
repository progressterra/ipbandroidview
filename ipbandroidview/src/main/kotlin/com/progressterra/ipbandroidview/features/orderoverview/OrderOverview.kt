package com.progressterra.ipbandroidview.features.orderoverview

import android.os.Parcelable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
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
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class OrderOverviewState(
    val quantity: Int = 0,
    val estimatedDelivery: String = "",
    val address: String = "",
    val images: List<String> = emptyList()
) : Parcelable

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
        Box(modifier = Modifier.size(width = 128.dp, height = 104.dp)) {
            state.images.forEachIndexed { index, image ->
                val horizontalPadding = 38.dp / (state.images.size - 1) * index
                val verticalPadding = 34.dp / (state.images.size - 1) * index
                Box(
                    modifier = Modifier

                        .align(Alignment.TopStart)
                        .padding(
                            start = horizontalPadding, top = verticalPadding
                        )
                ) {
                    SimpleImage(
                        modifier = Modifier
                            .size(width = 90.dp, height = 70.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        url = image,
                        backgroundColor = IpbTheme.colors.background.asColor()
                    )
                }
            }
        }
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            BrushedText(
                text = "${state.quantity} ${stringResource(R.string.items_paid)}",
                style = IpbTheme.typography.subHeadlineBold,
                tint = IpbTheme.colors.onBackground.asBrush()
            )
            BrushedText(
                text = stringResource(R.string.estimated_delivery),
                style = IpbTheme.typography.footnoteBold,
                tint = IpbTheme.colors.textTertiary.asBrush()
            )
            BrushedText(
                text = state.estimatedDelivery,
                style = IpbTheme.typography.footnoteRegular,
                tint = IpbTheme.colors.textTertiary.asBrush()
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
                quantity = 3,
                estimatedDelivery = "12.12.2021",
                address = "Karl-Marx-Allee 1, 10178 Berlin",
                images = listOf(
                    "https://images.unsplash.com/photo-1616489953143-8b8b2b2b2b1c?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
                    "https://images.unsplash.com/photo-1616489953143-8b8b2b2b2b1c?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
                    "https://images.unsplash.com/photo-1616489953143-8b8b2b2b2b1c?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80"
                )
            )
        )
    }
}