package com.progressterra.ipbandroidview.widgets.deliverypicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.addresssuggestions.AddressSuggestions
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField

@Composable
fun DeliveryPicker(
    modifier: Modifier = Modifier, state: DeliveryPickerState, useComponent: UseDeliveryPicker
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        BrushedText(
            text = stringResource(R.string.delivery),
            tint = IpbTheme.colors.textPrimary.asBrush(),
            style = IpbTheme.typography.title
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            BrushedText(
                text = stringResource(R.string.courier_delivery),
                tint = IpbTheme.colors.textPrimary.asBrush(),
                style = IpbTheme.typography.body
            )
            Spacer(Modifier.weight(1f))
            BrushedIcon(
                resId = R.drawable.ic_courier, tint = IpbTheme.colors.primary.asBrush()
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                state = state.address,
                useComponent = useComponent,
                hint = stringResource(R.string.address)
            )
            AddressSuggestions(
                state = state.suggestions,
                useComponent = useComponent
            )
        }
    }
}

