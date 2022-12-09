package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.element.LinkText
import com.progressterra.ipbandroidview.composable.element.LinkTextData
import com.progressterra.ipbandroidview.composable.element.ThemedButton
import com.progressterra.ipbandroidview.model.bonuses.BonusesInfo
import com.progressterra.ipbandroidview.model.store.OrderGoods
import com.progressterra.ipbandroidview.model.store.SimplePrice
import com.progressterra.ipbandroidview.theme.AppTheme

private val dividerWidth = 1.dp

@Immutable
interface ReceiptState {

    val totalPrice: SimplePrice

    val deliveryPrice: SimplePrice?

    val useBonuses: Boolean

    val availableBonuses: BonusesInfo

    val promoCode: SimplePrice?

    val goods: List<OrderGoods>

    val paymentReady: Boolean
}

@Composable
fun Receipt(
    modifier: Modifier = Modifier,
    state: () -> ReceiptState,
    payment: () -> Unit,
    openUrl: (String) -> Unit
) {

    Column(
        modifier = modifier
            .clip(
                AppTheme.shapes.medium.copy(
                    bottomStart = ZeroCornerSize, bottomEnd = ZeroCornerSize
                )
            )
            .background(AppTheme.colors.surfaces)
            .padding(AppTheme.dimensions.small),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.padding(AppTheme.dimensions.tiny),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium)
        ) {
            Text(
                text = state().totalPrice.toString(),
                color = AppTheme.colors.black,
                style = AppTheme.typography.title
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(AppTheme.colors.background)
                        .height(dividerWidth)
                )
                state().goods.forEach {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${it.name} x ${it.inCartCounter}",
                            color = AppTheme.colors.gray2,
                            style = AppTheme.typography.tertiaryText
                        )
                        Text(
                            text = it.totalPrice.toString(),
                            color = AppTheme.colors.gray1,
                            style = AppTheme.typography.tertiaryText
                        )
                    }
                }
                if (state().useBonuses) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(R.string.paid_with_bonuses),
                            color = AppTheme.colors.gray2,
                            style = AppTheme.typography.tertiaryText
                        )
                        Text(
                            text = "-${state().availableBonuses.quantity}",
                            color = AppTheme.colors.primary,
                            style = AppTheme.typography.tertiaryText
                        )
                    }
                }
                state().promoCode?.let {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(R.string.paid_with_promocode),
                            color = AppTheme.colors.gray2,
                            style = AppTheme.typography.tertiaryText
                        )
                        Text(
                            text = "-$it",
                            color = AppTheme.colors.primary,
                            style = AppTheme.typography.tertiaryText
                        )
                    }
                }
                state().deliveryPrice?.let {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(R.string.delivery),
                            color = AppTheme.colors.gray2,
                            style = AppTheme.typography.tertiaryText
                        )
                        Text(
                            text = it.toString(),
                            color = AppTheme.colors.gray1,
                            style = AppTheme.typography.tertiaryText
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(AppTheme.colors.background)
                        .height(dividerWidth)
                )
            }
        }
        Spacer(modifier = Modifier.height(AppTheme.dimensions.large))
        ThemedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = payment,
            text = stringResource(R.string.pay),
            enabled = state()::paymentReady
        )
        Spacer(modifier = Modifier.height(AppTheme.dimensions.large))
        LinkText(
            linkTextData = listOf(
                LinkTextData(text = stringResource(id = R.string.payment_0)),
                LinkTextData(
                    text = stringResource(id = R.string.user_agreement),
                    tag = "user agreement",
                    annotation = stringResource(id = R.string.user_agreement_url),
                    onClick = openUrl
                ),
                LinkTextData(text = stringResource(id = R.string.and)),
                LinkTextData(
                    text = stringResource(id = R.string.payment_agreement),
                    tag = "payment agreement",
                    annotation = stringResource(id = R.string.payment_agreement_url),
                    onClick = openUrl
                ),
                LinkTextData(text = stringResource(id = R.string.payment_1)),
                LinkTextData(
                    text = stringResource(id = R.string.service_name),
                    tag = "service",
                    annotation = stringResource(id = R.string.service_url),
                    onClick = openUrl
                )
            )
        )
        Spacer(modifier = Modifier.height(AppTheme.dimensions.medium))
        LinkText(
            linkTextData = listOf(
                LinkTextData(
                    text = stringResource(id = R.string.merchant_info),
                    tag = "merchant_info",
                    annotation = stringResource(id = R.string.merchant_info_url),
                    onClick = openUrl
                )
            )
        )
        Spacer(modifier = Modifier.height(AppTheme.dimensions.large))
    }
}