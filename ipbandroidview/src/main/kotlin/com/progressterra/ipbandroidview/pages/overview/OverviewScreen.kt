package com.progressterra.ipbandroidview.pages.overview

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.ChecklistDocument
import com.progressterra.ipbandroidview.entities.ChecklistStats
import com.progressterra.ipbandroidview.entities.OrganizationOverview
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.modifier.niceClickable
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState

import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OverviewScreen(
    state: OverviewState, useComponent: UseOverviewScreen
) {
    ThemedLayout { _, _ ->
        StateColumn(state = state.screen, useComponent = useComponent) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(IpbTheme.colors.surface.asBrush())
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    BrushedText(
                        text = stringResource(R.string.audits),
                        style = IpbTheme.typography.body,
                        tint = IpbTheme.colors.textPrimary.asBrush()
                    )
                    val pagerState = rememberPagerState(pageCount = { state.overviews.size })
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val scope = rememberCoroutineScope()
                        state.overviews.forEachIndexed { index, overview ->
                            val selected = pagerState.currentPage == index
                            val backgroundColor =
                                if (selected) IpbTheme.colors.background.asBrush() else IpbTheme.colors.surface.asBrush()
                            val textColor =
                                if (selected) IpbTheme.colors.textPrimary.asBrush() else IpbTheme.colors.textTertiary.asBrush()
                            val style =
                                if (selected) IpbTheme.typography.body else IpbTheme.typography.body2
                            Box(modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .border(
                                    width = 1.dp,
                                    brush = IpbTheme.colors.background.asBrush(),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .background(backgroundColor)
                                .niceClickable {
                                    scope.launch {
                                        pagerState.animateScrollToPage(index)
                                    }
                                }
                                .padding(
                                    vertical = 8.dp, horizontal = 32.dp
                                ), contentAlignment = Alignment.Center) {
                                BrushedText(
                                    text = overview.name,
                                    tint = textColor,
                                    style = style,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    HorizontalPager(
                        state = pagerState
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            RowItem(
                                modifier = Modifier.niceClickable {
                                    useComponent.handle(OverviewEvent.Ongoing(state.overviews[it].ongoing))
                                },
                                title = stringResource(R.string.ongoing),
                                value = state.overviews[it].ongoing.size.toString()
                            )
                            RowItem(
                                modifier = Modifier.niceClickable {
                                    useComponent.handle(OverviewEvent.Complete(state.overviews[it].completed))
                                },
                                title = stringResource(R.string.completed),
                                value = state.overviews[it].completed.size.toString()
                            )
                            RowItem(
                                modifier = Modifier.niceClickable {
                                    useComponent.handle(OverviewEvent.Archived(state.overviews[it].archived))
                                },
                                title = stringResource(R.string.archived),
                                value = state.overviews[it].archived.size.toString()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RowItem(
    modifier: Modifier = Modifier, title: String, value: String
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        BrushedText(
            text = title,
            style = IpbTheme.typography.footnoteRegular,
            tint = IpbTheme.colors.textTertiary.asBrush(),
        )
        Spacer(modifier = Modifier.weight(1f))
        BrushedText(
            text = value, style = IpbTheme.typography.body,
            tint = IpbTheme.colors.textPrimary.asBrush()
        )
        Spacer(modifier = Modifier.width(16.dp))
        BrushedIcon(
            modifier = modifier,
            resId = R.drawable.ic_forward,
            tint = IpbTheme.colors.iconTertiary.asBrush()
        )
    }
}

@Preview
@Composable
fun OverviewScreenPreview() {
    OverviewScreen(
        state = OverviewState(
            screen = StateColumnState(state = ScreenState.SUCCESS),
            overviews = listOf(
                OrganizationOverview(
                    name = "Organization 1",
                    ongoing = listOf(
                        ChecklistDocument(
                            documentId = "1",
                            checklistId = "1",
                            placeId = "1",
                            name = "Checklist 1",
                            address = "Address 1",
                            checkCounter = 1,
                            finishDate = null,
                            isRecentlyFinished = false,
                            stats = ChecklistStats(
                                total = 1,
                                successful = 1,
                                failed = 0,
                                remaining = 0
                            )
                        ),
                        ChecklistDocument(
                            documentId = "2",
                            checklistId = "2",
                            placeId = "2",
                            name = "Checklist 2",
                            address = "Address 2",
                            checkCounter = 1,
                            finishDate = null,
                            isRecentlyFinished = false,
                            stats = ChecklistStats(
                                total = 1,
                                successful = 1,
                                failed = 0,
                                remaining = 0
                            )
                        )
                    ),
                    completed = listOf(),
                    archived = listOf()
                ),
                OrganizationOverview(
                    name = "Organization 2",
                    ongoing = listOf(),
                    completed = listOf(),
                    archived = listOf()
                ),
                OrganizationOverview(
                    name = "Organization 3",
                    ongoing = listOf(),
                    completed = listOf(),
                    archived = listOf()
                )
            )
        ),
        useComponent = UseOverviewScreen.Empty()
    )
}