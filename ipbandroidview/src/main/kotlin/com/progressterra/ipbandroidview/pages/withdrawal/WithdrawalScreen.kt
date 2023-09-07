package com.progressterra.ipbandroidview.pages.withdrawal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.features.withdrawaltransaction.WithdrawalTransaction
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn

@Composable
fun WithdrawalScreen(
    modifier: Modifier = Modifier,
    state: WithdrawalScreenState,
    useComponent: UseWithdrawalScreen
) {
    ThemedLayout(
        modifier = modifier,
        topBar = {
            TopBar(
                title = stringResource(id = R.string.withdrawal),
                useComponent = useComponent,
                showBackButton = true
            )
        }
    ) { _, _ ->
        StateColumn(
            state = state.screen,
            useComponent = useComponent,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 20.dp, end = 20.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(IpbTheme.colors.surface.asBrush())
                    .padding(6.dp)
            ) {
                BrushedText(
                    text = stringResource(id = R.string.can_be_out),
                    style = IpbTheme.typography.footnoteBold,
                    tint = IpbTheme.colors.textSecondary.asBrush()
                )
                BrushedText(
                    text = state.canBeWithdrawal.toString(),
                    style = IpbTheme.typography.title,
                    tint = IpbTheme.colors.textPrimary.asBrush()
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                state = state.add,
                title = stringResource(id = R.string.new_withdrawal),
                useComponent = useComponent
            )
            Spacer(modifier = Modifier.height(40.dp))
            BrushedText(
                text = stringResource(id = R.string.withdrawal_history),
                style = IpbTheme.typography.title,
                tint = IpbTheme.colors.textPrimary.asBrush()
            )
            Spacer(modifier = Modifier.height(20.dp))
            val lazyItems = state.transactions.collectAsLazyPagingItems()
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 20.dp)
            ) {
                items(
                    count = lazyItems.itemCount,
                    key = lazyItems.itemKey { it.id }
                ) { index -> lazyItems[index]?.let { WithdrawalTransaction(state = it) } }
            }
        }
    }
}