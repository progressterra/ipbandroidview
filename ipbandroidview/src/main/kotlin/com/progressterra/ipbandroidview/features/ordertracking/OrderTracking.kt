package com.progressterra.ipbandroidview.features.ordertracking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidapi.api.cart.models.TypeStatusOrder
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.toString
import com.progressterra.ipbandroidview.shared.ManageResources
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Composable
fun OrderTracking(
    modifier: Modifier = Modifier, state: OrderTrackingState
) {

    val steps = mapOf(
        TypeStatusOrder.CONFIRM_FROM_STORE to 0,
        TypeStatusOrder.CONFIRM_FROM_CALL_CENTER to 1,
        TypeStatusOrder.SENT_TO_WAREHOUSE to 2,
        TypeStatusOrder.SENT_DELIVERY_SERVICE to 3,
        TypeStatusOrder.ON_PICK_UP_POINT to 4,
        TypeStatusOrder.DELIVERED to 5
    )

    @Composable
    fun Item(
        itemState: TypeStatusOrder
    ) {
        val brush =
            if (state.status == itemState) IpbTheme.colors.textPrimary.asBrush() else IpbTheme.colors.textSecondary.asBrush()
        val style =
            if (state.status == itemState) IpbTheme.typography.body else IpbTheme.typography.subHeadlineRegular

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (steps[state.status]!! >= steps[itemState]!!) {
                Box(modifier = Modifier.size(20.dp), contentAlignment = Alignment.Center) {
                    BrushedIcon(
                        modifier = Modifier.size(if (state.status == itemState) 16.dp else 12.dp),
                        resId = R.drawable.ic_dot,
                        tint = IpbTheme.colors.primary.asBrush()
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(20.dp))
            }
            BrushedText(
                text = itemState.toString(ManageResources.Base(LocalContext.current)),
                tint = brush,
                style = style
            )
        }
    }

    if (steps.containsKey(state.status)) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(IpbTheme.colors.surface.asBrush())
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                BrushedText(
                    text = state.status.toString(ManageResources.Base(LocalContext.current)),
                    tint = IpbTheme.colors.textPrimary.asBrush(),
                    style = IpbTheme.typography.title
                )
                BrushedText(
                    text = state.number,
                    tint = IpbTheme.colors.textTertiary.asBrush(),
                    style = IpbTheme.typography.footnoteRegular
                )
            }
            steps.keys.forEach { Item(itemState = it) }
        }
    }
}
