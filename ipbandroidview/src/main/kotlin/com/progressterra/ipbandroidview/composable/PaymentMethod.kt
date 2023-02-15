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
import com.progressterra.ipbandroidview.theme.AppTheme

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
            modifier = modifier.padding(vertical = AppTheme.dimensions.small),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium)
        ) {
            ThemedRadioButton(
                checked = type == state.selectedPaymentMethod,
                onClick = { select(type) }
            )
            Text(
                text = stringResource(type.paymentName),
                color = AppTheme.colors.black,
                style = AppTheme.typography.text
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .padding(AppTheme.dimensions.medium),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
    ) {
        Text(
            text = stringResource(R.string.payment),
            color = AppTheme.colors.black,
            style = AppTheme.typography.title
        )
        Column(verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiny)) {
            state.paymentMethods.forEach {
                Item(type = it)
            }
        }
    }
}