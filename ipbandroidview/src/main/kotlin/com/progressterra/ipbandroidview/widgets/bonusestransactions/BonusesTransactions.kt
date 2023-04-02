package com.progressterra.ipbandroidview.widgets.bonusestransactions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.features.bonustransaction.BonusTransaction
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedDivider

@Composable
fun BonusesTransactions(
    modifier: Modifier = Modifier,
    state: BonusesTransactionsState
) {
    LazyColumn(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(IpbTheme.colors.surface1.asBrush()),
        contentPadding = PaddingValues(24.dp)
    ) {
        itemsIndexed(state.transactions) { index, item ->
            BonusTransaction(
                state = item
            )
            if (index != state.transactions.lastIndex) {
                BrushedDivider(tint = IpbTheme.colors.background.asColor())
            }
        }
    }
}