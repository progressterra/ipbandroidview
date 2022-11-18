package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.progressterra.ipbandroidview.theme.AppTheme

private val dividerWidth = 1.dp

@Immutable
interface ReceiptState {

    val totalPrice: String

    val deliveryPrice: String

    val paidWithBonuses: String

    val promoCode: String

    val goodsReceipts: List<GoodsReceipt>

    val paymentReady: Boolean
}

@Immutable
interface GoodsReceipt {

    val name: String

    val count: Int

    val totalPrice: String
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
                text = state().totalPrice,
                color = AppTheme.colors.black,
                style = AppTheme.typography.title
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium),
                userScrollEnabled = false
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(AppTheme.colors.background)
                            .height(dividerWidth)
                    )
                }
                items(state().goodsReceipts) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${it.name} x ${it.count}",
                            color = AppTheme.colors.gray2,
                            style = AppTheme.typography.tertiaryText
                        )
                        Text(
                            text = it.totalPrice,
                            color = AppTheme.colors.gray1,
                            style = AppTheme.typography.tertiaryText
                        )
                    }
                }
                if (state().paidWithBonuses.isNotBlank()) {
                    item {
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
                                text = "-${state().paidWithBonuses}",
                                color = AppTheme.colors.primary,
                                style = AppTheme.typography.tertiaryText
                            )
                        }
                    }
                }
                if (state().promoCode.isNotBlank()) {
                    item {
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
                                text = "-${state().promoCode}",
                                color = AppTheme.colors.primary,
                                style = AppTheme.typography.tertiaryText
                            )
                        }
                    }
                }
                if (state().deliveryPrice.isNotBlank()) {
                    item {
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
                                text = state().deliveryPrice,
                                color = AppTheme.colors.gray1,
                                style = AppTheme.typography.tertiaryText
                            )
                        }
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(AppTheme.colors.background)
                            .height(dividerWidth)
                    )
                }
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