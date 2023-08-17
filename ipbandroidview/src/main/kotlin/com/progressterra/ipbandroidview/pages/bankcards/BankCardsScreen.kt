package com.progressterra.ipbandroidview.pages.bankcards

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.progressterra.ipbandroidview.shared.Tabs
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumn
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
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
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(IpbTheme.colors.surface.asBrush())
                    .padding(8.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    state = state.add,
                    title = stringResource(id = R.string.add_card),
                    useComponent = useComponent
                )
            }
        }
    ) { _, _ ->
        StateColumn(state = state.screen, useComponent = useComponent) {
            val pagerState = rememberPagerState { 2 }
            val scope = rememberCoroutineScope()
            Tabs(
                modifier = Modifier.padding(horizontal = 20.dp),
                tabs = listOf(
                    stringResource(id = R.string.added_cards),
                    stringResource(id = R.string.other)
                ),
                currentIndex = pagerState.currentPage,
                onTabClicked = { scope.launch { pagerState.animateScrollToPage(it) } }
            )
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 20.dp),
                pageSpacing = 20.dp,
                verticalAlignment = Alignment.Top
            ) { page ->
                if (page == 0) {
                    val lazyItems = state.addedCards.collectAsLazyPagingItems()
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(top = 20.dp, start = 20.dp, end = 20.dp)
                    ) {
                        items(
                            count = lazyItems.itemCount,
                            key = lazyItems.itemKey { it.id }
                        ) { index ->
                            lazyItems[index]?.let {
                                BankCard(
                                    state = it,
                                    useComponent = useComponent,
                                    canBePicked = false
                                )
                            }
                        }
                    }
                } else if (page == 1) {
                    val lazyItems = state.otherCards.collectAsLazyPagingItems()
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(top = 20.dp, start = 20.dp, end = 20.dp)
                    ) {
                        items(
                            count = lazyItems.itemCount,
                            key = lazyItems.itemKey { it.id }
                        ) { index ->
                            lazyItems[index]?.let {
                                BankCard(
                                    state = it,
                                    useComponent = useComponent,
                                    canBePicked = false
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}