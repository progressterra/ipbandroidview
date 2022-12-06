package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.element.ForwardIcon
import com.progressterra.ipbandroidview.composable.element.ThemedRadioButton
import com.progressterra.ipbandroidview.composable.element.ThemedTextField
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.model.AddressUI
import com.progressterra.ipbandroidview.model.Delivery
import com.progressterra.ipbandroidview.theme.AppTheme

interface DeliveryPickerState {

    val addressUI: AddressUI

    val selectedDeliveryMethod: Delivery?

    val deliveryMethods: List<Delivery>
}

@Composable
fun DeliveryPicker(
    modifier: Modifier = Modifier,
    state: () -> DeliveryPickerState,
    changeAddress: () -> Unit,
    selectPickUpPoint: () -> Unit,
    selectDeliveryMethod: (Delivery) -> Unit,
    editComment: (String) -> Unit
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
            text = stringResource(R.string.delivery),
            color = AppTheme.colors.black,
            style = AppTheme.typography.title
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(AppTheme.shapes.medium)
                .background(AppTheme.colors.background)
                .niceClickable(onClick = changeAddress)
                .padding(AppTheme.dimensions.large),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = state().addressUI.printAddress(),
                color = AppTheme.colors.black,
                style = AppTheme.typography.text
            )
            ForwardIcon()
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
        ) {
            state().deliveryMethods.forEach {
                Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium)) {
                    ThemedRadioButton(
                        checked = { it == state().selectedDeliveryMethod },
                        onClick = { selectDeliveryMethod(it) })
                    Column {
                        Text(
                            text = "${it.date}, ${it.price}",
                            color = AppTheme.colors.black,
                            style = AppTheme.typography.text
                        )
                        Text(
                            text = it.type,
                            color = AppTheme.colors.black,
                            style = AppTheme.typography.text
                        )
                    }
                }
                if (it == state().selectedDeliveryMethod)
                    when (it) {
                        is Delivery.CourierDelivery -> ThemedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            text = it::commentary,
                            hint = stringResource(R.string.comment),
                            onChange = editComment
                        )
                        is Delivery.PickUpPointDelivery -> Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(AppTheme.shapes.medium)
                                .background(AppTheme.colors.background)
                                .niceClickable(onClick = selectPickUpPoint)
                                .padding(AppTheme.dimensions.large),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = it.currentPoint.address,
                                color = AppTheme.colors.black,
                                style = AppTheme.typography.text
                            )
                            ForwardIcon()
                        }
                    }
            }
        }
    }
}

