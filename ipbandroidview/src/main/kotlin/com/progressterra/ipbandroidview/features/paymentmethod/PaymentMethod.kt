package com.progressterra.ipbandroidview.features.paymentmethod

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.payment.PaymentType
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.ThemedRadioButton

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
            BrushedText(
                text = stringResource(type.paymentName),
                tint = IpbTheme.colors.textPrimary1.asBrush(),
                style = IpbTheme.typography.primary
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(IpbTheme.colors.surface1.asBrush())
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        BrushedText(
            text = stringResource(R.string.payment),
            tint = IpbTheme.colors.textPrimary1.asBrush(),
            style = IpbTheme.typography.title
        )
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            state.paymentMethods.forEach {
                Item(type = it)
            }
        }
    }
}