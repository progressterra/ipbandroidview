package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.model.PaymentType
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Immutable
interface PaymentMethodState {

    val selectedPaymentMethod: PaymentType?

    val paymentMethods: List<PaymentType>
}

@Composable
fun PaymentMethod(
    modifier: Modifier = Modifier,
    select: (PaymentType) -> Unit,
    state: PaymentMethodState
) {

    @Composable
    fun Item(
        modifier: Modifier = Modifier,
        type: PaymentType
    ) {
        Row(
            modifier = modifier.padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ThemedRadioButton(
                checked = type == state.selectedPaymentMethod,
                onClick = { select(type) }
            )
            Text(
                text = stringResource(type.paymentName),
                color = IpbTheme.colors.black,
                style = IpbTheme.typography.text
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(IpbTheme.shapes.medium)
            .background(IpbTheme.colors.surfaces)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.payment),
            color = IpbTheme.colors.black,
            style = IpbTheme.typography.title
        )
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            state.paymentMethods.forEach {
                Item(type = it)
            }
        }
    }
}