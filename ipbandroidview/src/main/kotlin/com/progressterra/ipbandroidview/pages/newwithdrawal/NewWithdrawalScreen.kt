package com.progressterra.ipbandroidview.pages.newwithdrawal

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
import com.progressterra.ipbandroidview.features.bankcard.BankCard
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.TextButton
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField

@Composable
fun NewWithdrawalScreen(
    modifier: Modifier = Modifier,
    state: NewWithdrawalScreenState,
    useComponent: UseNewWithdrawalScreen
) {
    ThemedLayout(
        modifier = modifier,
        topBar = {
            TopBar(
                title = stringResource(id = R.string.withdrawal),
                useComponent = useComponent,
                showBackButton = true
            )
        }, bottomBar = {
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(IpbTheme.colors.surface.asBrush())
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 36.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    state = state.add,
                    title = stringResource(id = R.string.withdraw),
                    useComponent = useComponent
                )
            }
        }
    ) { _, _ ->
        StateColumn(
            state = state.screen,
            useComponent = useComponent,
            horizontalAlignment = Alignment.End
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp)
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
            val lazyItems = state.cards.collectAsLazyPagingItems()
            LazyColumn(
                modifier = Modifier.height(200.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 20.dp)
            ) {
                items(
                    count = lazyItems.itemCount,
                    key = lazyItems.itemKey { it.id }
                ) { index ->
                    lazyItems[index]?.let {
                        BankCard(
                            state = it,
                            useComponent = useComponent
                        )
                    }
                }
            }
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                state = state.input, useComponent = useComponent,
                hint = stringResource(id = R.string.input_amount)
            )
            TextButton(
                modifier = Modifier.padding(horizontal = 20.dp),
                state = state.all,
                title = stringResource(id = R.string.withdraw_all),
                useComponent = useComponent,
                isTiny = true
            )
        }
    }
}