package com.progressterra.ipbandroidview.pages.bankcards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.bankcard.Card
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button

@Composable
fun BankCardsScreen(
    modifier: Modifier = Modifier,
    state: BankCardsScreenState,
    useComponent: UseBankCardsScreen
) {
    ThemedLayout(
        modifier = modifier,
        topBar = {
            TopBar(
                title = stringResource(id = R.string.bank_cards),
                useComponent = useComponent,
                showBackButton = true
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(IpbTheme.colors.surface.asBrush())
                    .padding(8.dp)
            ) {
                Button(
                    state = state.add,
                    title = stringResource(id = R.string.add_card),
                    useComponent = useComponent
                )
            }
        }
    ) { _, _ ->
        val lazyItems = state.cards.collectAsLazyPagingItems()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(top = 20.dp, start = 20.dp, end = 20.dp)
        ) {
            items(
                count = lazyItems.itemCount,
                key = lazyItems.itemKey { it.id }
            ) { index ->
                lazyItems[index]?.let { Card(state = it, useComponent = useComponent) }
            }
        }
    }
}