package com.progressterra.ipbandroidview.widgets.deliverypicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.Delivery
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.ThemedRadioButton
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.ButtonStyle
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField

@Composable
fun DeliveryPicker(
    modifier: Modifier = Modifier, state: DeliveryPickerState, useComponent: UseDeliveryPicker
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        BrushedText(
            text = stringResource(R.string.delivery),
            tint = IpbTheme.colors.textPrimary.asBrush(),
            style = IpbTheme.typography.title
        )
        state.deliveryMethods.forEach {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ThemedRadioButton(checked = it == state.selectedDeliveryMethod, onClick = {
                    useComponent.handle(DeliveryPickerEvent.SelectDeliveryMethod(it))
                })
                val textColor =
                    if (it == state.selectedDeliveryMethod) IpbTheme.colors.textPrimary else IpbTheme.colors.textDisabled
                val textStyle =
                    if (it == state.selectedDeliveryMethod) IpbTheme.typography.body else IpbTheme.typography.subHeadlineRegular
                val icon = when (it) {
                    is Delivery.CourierDelivery -> R.drawable.ic_courier
                    is Delivery.PickUpPointDelivery -> R.drawable.ic_pickup_point
                }
                val iconColor =
                    if (it == state.selectedDeliveryMethod) IpbTheme.colors.primary else IpbTheme.colors.textDisabled
                BrushedText(
                    text = it.type,
                    tint = textColor.asBrush(),
                    style = textStyle
                )
                Spacer(Modifier.weight(1f))
                BrushedIcon(
                    resId = icon,
                    tint = iconColor.asBrush()
                )
            }
            if (it == state.selectedDeliveryMethod) when (it) {
                is Delivery.CourierDelivery -> {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        TextField(
                            state = state.city,
                            useComponent = useComponent,
                            hint = stringResource(R.string.city),
                            actionIcon = R.drawable.ic_cancel_pro
                        )
                        TextField(
                            state = state.home,
                            useComponent = useComponent,
                            hint = stringResource(R.string.home),
                            actionIcon = R.drawable.ic_cancel_pro
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            TextField(
                                state = state.entrance,
                                useComponent = useComponent,
                                hint = stringResource(R.string.entrance),
                                actionIcon = R.drawable.ic_cancel_pro
                            )
                            TextField(
                                state = state.apartment,
                                useComponent = useComponent,
                                hint = stringResource(R.string.apartment),
                                actionIcon = R.drawable.ic_cancel_pro
                            )
                        }
                    }
                }
                is Delivery.PickUpPointDelivery -> {
                    if (!it.currentPoint.isEmpty()) {
                        TextField(
                            state = state.address,
                            useComponent = useComponent,
                            hint = stringResource(R.string.address),
                            actionIcon = R.drawable.ic_cancel_pro
                        )
                    }
                    Button(
                        state = state.selectPoint,
                        useComponent = useComponent,
                        title = stringResource(R.string.select_pickup_point),
                        style = ButtonStyle.OUTLINE
                    )
                }
            }
        }

    }
}

