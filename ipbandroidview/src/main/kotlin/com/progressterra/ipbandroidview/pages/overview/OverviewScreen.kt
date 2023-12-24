package com.progressterra.ipbandroidview.pages.overview

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.progressterra.ipbandroidview.features.stats.Stats
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.Tabs
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.modifier.niceClickable
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OverviewScreen(
    state: OverviewState, useComponent: UseOverviewScreen
) {
    ThemedLayout(
        topBar = {
            TopBar(title = stringResource(id = R.string.audits), useComponent = useComponent)
        }
    ) { _, _ ->
        StateColumn(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            state = state.screen,
            useComponent = useComponent,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val tabs = listOf(
                stringResource(id = R.string.ongoing),
                stringResource(id = R.string.archived)
            )
            val pagerState = rememberPagerState(pageCount = { tabs.size })
            val scope = rememberCoroutineScope()
            Tabs(tabs = tabs, currentIndex = pagerState.currentPage, onTabClicked = {
                scope.launch { pagerState.animateScrollToPage(it) }
            })
            HorizontalPager(state = pagerState) { pageNumber ->
                val lazyItems =
                    (if (pageNumber == 0) state.ongoing else state.archived).collectAsLazyPagingItems()
                val cardBackground =
                    if (pageNumber == 0) IpbTheme.colors.tertiary.asBrush() else IpbTheme.colors.surface.asBrush()
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(lazyItems.itemCount, key = lazyItems.itemKey { it.documentId }) { index ->
                        lazyItems[index]?.let {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(cardBackground)
                                    .niceClickable {
                                        useComponent.handle(OverviewEvent.OnChecklist(it))
                                    }
                                    .padding(12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                    BrushedText(
                                        text = it.name,
                                        style = IpbTheme.typography.title2,
                                        tint = IpbTheme.colors.textPrimary.asBrush()
                                    )
                                    BrushedText(
                                        text = it.address,
                                        style = IpbTheme.typography.footnoteRegular,
                                        tint = IpbTheme.colors.textTertiary.asBrush()
                                    )
                                    Stats(modifier = Modifier.width(200.dp), stats = it.stats)
                                }
                                BrushedIcon(
                                    modifier = Modifier.size(width = 10.dp, height = 17.dp),
                                    resId = R.drawable.ic_forward,
                                    tint = IpbTheme.colors.iconTertiary.asBrush()
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}