package com.progressterra.ipbandroidview.composable

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
import com.progressterra.ipbandroidview.composable.component.TextField
import com.progressterra.ipbandroidview.composable.component.UseTextField
import com.progressterra.ipbandroidview.shared.ui.niceClickable
import com.progressterra.ipbandroidview.model.AddressUI
import com.progressterra.ipbandroidview.model.Delivery
import com.progressterra.ipbandroidview.model.DeliveryType
import com.progressterra.ipbandroidview.theme.AppTheme

data class DeliveryPickerState(
    val addressUI: AddressUI = AddressUI(),
    val selectedDeliveryMethod: Delivery? = null,
    val deliveryMethods: Map<DeliveryType, Delivery> = emptyMap(),
)

interface UseDeliveryPicker : UseTextField {

    fun handleEvent(id: String, event: DeliveryPickerEvent)
}

sealed class DeliveryPickerEvent {
    data class ChangeAddress(val addressUI: AddressUI) : DeliveryPickerEvent()
    data class SelectDeliveryMethod(val delivery: Delivery) : DeliveryPickerEvent()

    object SelectPickupPoint : DeliveryPickerEvent()
}

/**
 * commentary - text field
 */
@Composable
fun DeliveryPicker(
    modifier: Modifier = Modifier,
    id: String,
    state: DeliveryPickerState,
    useComponent: UseDeliveryPicker
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
        Row(modifier = Modifier
            .fillMaxWidth()
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.background)
            .niceClickable {
                useComponent.handleEvent(
                    id = id, event = DeliveryPickerEvent.ChangeAddress(state.addressUI)
                )
            }
            .padding(AppTheme.dimensions.large),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = state.addressUI.printAddress(),
                color = AppTheme.colors.black,
                style = AppTheme.typography.text
            )
            ForwardIcon()
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
        ) {
            state.deliveryMethods.values.forEach {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ThemedRadioButton(checked = it == state.selectedDeliveryMethod, onClick = {
                        useComponent.handleEvent(
                            id = id, event = DeliveryPickerEvent.SelectDeliveryMethod(it)
                        )
                    })
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
                if (it == state.selectedDeliveryMethod) when (it) {
                    is Delivery.CourierDelivery -> TextField(
                        modifier = Modifier.fillMaxWidth(),
                        id = "commentary",
                        state = it.commentary,
                        useComponent = useComponent
                    )
                    is Delivery.PickUpPointDelivery -> Row(modifier = Modifier
                        .fillMaxWidth()
                        .clip(AppTheme.shapes.medium)
                        .background(AppTheme.colors.background)
                        .niceClickable {
                            useComponent.handleEvent(
                                id = id, event = DeliveryPickerEvent.SelectPickupPoint
                            )
                        }
                        .padding(AppTheme.dimensions.large),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically) {
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

