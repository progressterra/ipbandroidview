package com.progressterra.ipbandroidview.features.buygoods

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.OutlineButton

@Composable
fun BuyGoods(
    modifier: Modifier = Modifier,
    state: BuyGoodsState,
    useComponent: UseBuyGoods
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            BrushedText(
                text = state.price.toString(),
                style = IpbTheme.typography.title,
                tint = IpbTheme.colors.textPrimary.asBrush()
            )
            Button(
                modifier = Modifier.weight(1f),
                state = state.buy,
                useComponent = useComponent,
                title = stringResource(R.string.add_to_cart)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            BrushedText(
                modifier = Modifier.widthIn(max = 100.dp),
                text = state.loan,
                style = IpbTheme.typography.body,
                tint = IpbTheme.colors.textPrimary.asBrush()
            )
            OutlineButton(
                modifier = Modifier.weight(1f),
                state = state.buyWithLoan,
                useComponent = useComponent,
                title = stringResource(R.string.buy_with_loan)
            )
        }
    }
}