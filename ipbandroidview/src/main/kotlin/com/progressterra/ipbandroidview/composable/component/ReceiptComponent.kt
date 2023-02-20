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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.LinkText
import com.progressterra.ipbandroidview.composable.LinkTextData
import com.progressterra.ipbandroidview.core.ComponentEvent
import com.progressterra.ipbandroidview.model.OrderGoods
import com.progressterra.ipbandroidview.model.SimplePrice
import com.progressterra.ipbandroidview.theme.AppTheme

private val dividerWidth = 1.dp

@Immutable
data class ReceiptComponentState(
    val totalPrice: SimplePrice = SimplePrice(),
    val deliveryPrice: SimplePrice = SimplePrice(),
    val useBonuses: Boolean = false,
    val availableBonuses: BonusesComponentState = BonusesComponentState(),
    val promoCode: SimplePrice = SimplePrice(),
    val goods: List<OrderGoods> = emptyList(),
    val paymentReady: Boolean = false
)

sealed class ReceiptComponentEvent : ComponentEvent {

    object Payment : ReceiptComponentEvent()

    data class OpenUrl(val url: String) : ReceiptComponentEvent()
}

/**
 * @param modifier - modifier for component
 * @param state - state of component
 * @param onEvent - callback for events
 */
@Composable
fun ReceiptComponent(
    modifier: Modifier = Modifier,
    state: ReceiptComponentState,
    onEvent: (ReceiptComponentEvent) -> Unit
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
                text = state.totalPrice.toString(),
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
                state.goods.forEach {
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
                if (state.useBonuses) {
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
                            text = "-${state.availableBonuses.bonuses}",
                            color = AppTheme.colors.primary,
                            style = AppTheme.typography.tertiaryText
                        )
                    }
                }
                if (!state.promoCode.isEmpty()) {
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
                            text = "-${state.promoCode}",
                            color = AppTheme.colors.primary,
                            style = AppTheme.typography.tertiaryText
                        )
                    }
                }
                if (!state.deliveryPrice.isEmpty()) {
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
                            text = state.deliveryPrice.toString(),
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
        ButtonComponent(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onEvent(ReceiptComponentEvent.Payment) },
            text = stringResource(R.string.pay),
            enabled = state.paymentReady
        )
        Spacer(modifier = Modifier.height(AppTheme.dimensions.large))
        LinkText(
            linkTextData = listOf(
                LinkTextData(text = stringResource(id = R.string.payment_0)), LinkTextData(
                    text = stringResource(id = R.string.user_agreement),
                    tag = "user agreement",
                    annotation = stringResource(id = R.string.user_agreement_url),
                    onClick = { onEvent(ReceiptComponentEvent.OpenUrl(it)) }
                ), LinkTextData(text = stringResource(id = R.string.and)), LinkTextData(
                    text = stringResource(id = R.string.payment_agreement),
                    tag = "payment agreement",
                    annotation = stringResource(id = R.string.payment_agreement_url),
                    onClick = { onEvent(ReceiptComponentEvent.OpenUrl(it)) }
                ), LinkTextData(text = stringResource(id = R.string.payment_1)), LinkTextData(
                    text = stringResource(id = R.string.service_name),
                    tag = "service",
                    annotation = stringResource(id = R.string.service_url),
                    onClick = { onEvent(ReceiptComponentEvent.OpenUrl(it)) }
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
                    onClick = { onEvent(ReceiptComponentEvent.OpenUrl(it)) }
                )
            )
        )
        Spacer(modifier = Modifier.height(AppTheme.dimensions.large))
    }
}

@Composable
@Preview
private fun ReceiptComponentPreview() {
    ReceiptComponent(
        state = ReceiptComponentState(
            totalPrice = SimplePrice(1000),
            deliveryPrice = SimplePrice(100),
            useBonuses = true,
            availableBonuses = BonusesComponentState(),
            promoCode = SimplePrice(100),
            goods = listOf(
                OrderGoods(
                    id = "1",
                    name = "Товар 1",
                    inCartCounter = 1,
                    totalPrice = SimplePrice(1000),
                    image = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"
                )
            ),
            paymentReady = true
        ),
        onEvent = {}
    )
}