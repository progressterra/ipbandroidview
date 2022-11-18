package com.progressterra.ipbandroidview.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.AppTheme

@Immutable
interface PaymentMethodState {

    val currentPaymentMethod: PaymentType?
}

@Immutable
enum class PaymentType(@StringRes val paymentName: Int) {
    ONLINE_CARD(R.string.online_card),
    OFFLINE_CARD(R.string.offline_card)
}

@Composable
fun PaymentMethod(
    modifier: Modifier = Modifier,
    select: (PaymentType) -> Unit,
    state: () -> PaymentMethodState
) {

    @Composable
    fun Item(
        modifier: Modifier = Modifier,
        type: PaymentType
    ) {
        Row(
            modifier = modifier.padding(AppTheme.dimensions.small),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium)
        ) {
            ThemedRadioButton(
                checked = { type == state().currentPaymentMethod },
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
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .padding(AppTheme.dimensions.medium),
    ) {
        Text(
            text = stringResource(R.string.payment),
            color = AppTheme.colors.black,
            style = AppTheme.typography.title
        )
        Spacer(Modifier.size(AppTheme.dimensions.small))
        Item(type = PaymentType.ONLINE_CARD)
        Spacer(Modifier.size(AppTheme.dimensions.tiny))
        Item(type = PaymentType.OFFLINE_CARD)
    }
}