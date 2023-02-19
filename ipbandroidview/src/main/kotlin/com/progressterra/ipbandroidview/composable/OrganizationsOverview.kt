package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.model.Document
import com.progressterra.ipbandroidview.theme.AppTheme
import kotlinx.coroutines.launch

data class OrganizationOverview(
    val name: String,
    val ongoing: List<Document>,
    val completed: List<Document>,
    val archived: List<Document>
)

private val borderWidth = 1.dp

private val tabHorizontalPadding = 32.dp

@Composable
fun RowItem(
    modifier: Modifier = Modifier, title: String, value: String
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = AppTheme.typography.secondaryText,
            color = AppTheme.colors.gray1,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = value, style = AppTheme.typography.text, color = AppTheme.colors.black
        )
        Spacer(modifier = Modifier.width(AppTheme.dimensions.large))
        ForwardIcon()
    }
}

sealed class OrganizationsOverviewEvent {

    data class Archived(val documents: List<Document>) : OrganizationsOverviewEvent()

    data class Complete(val documents: List<Document>) : OrganizationsOverviewEvent()

    data class Ongoing(val documents: List<Document>) : OrganizationsOverviewEvent()
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OrganizationsOverview(
    modifier: Modifier = Modifier,
    overviews: List<OrganizationOverview>,
    onEvent: (OrganizationsOverviewEvent) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .padding(AppTheme.dimensions.medium),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.large)
    ) {
        Text(
            text = stringResource(R.string.audits),
            style = AppTheme.typography.text,
            color = AppTheme.colors.black
        )
        val pagerState = rememberPagerState()
        ScrollableTabRow(selectedTabIndex = pagerState.currentPage,
            backgroundColor = AppTheme.colors.surfaces,
            indicator = {},
            divider = {
                Spacer(modifier = Modifier.width(AppTheme.dimensions.large))
            }) {
            val scope = rememberCoroutineScope()
            overviews.forEachIndexed { index, overview ->
                val selected = pagerState.currentPage == index
                val backgroundColor =
                    if (selected) AppTheme.colors.background else AppTheme.colors.surfaces
                Tab(modifier = Modifier
                    .clip(AppTheme.shapes.small)
                    .border(
                        width = borderWidth,
                        color = AppTheme.colors.background,
                        shape = AppTheme.shapes.small
                    ), selected = selected, onClick = {
                    scope.launch { pagerState.animateScrollToPage(page = index) }
                }) {
                    val textColor = if (selected) AppTheme.colors.black else AppTheme.colors.gray1
                    val style =
                        if (selected) AppTheme.typography.text else AppTheme.typography.secondaryText
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(AppTheme.shapes.small)
                            .background(backgroundColor)
                            .padding(
                                vertical = AppTheme.dimensions.small,
                                horizontal = tabHorizontalPadding
                            ), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = overview.name,
                            color = textColor,
                            style = style,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
        HorizontalPager(
            count = overviews.size, state = pagerState, itemSpacing = AppTheme.dimensions.large
        ) {
            RowItem(
                modifier = Modifier.niceClickable {
                    onEvent(
                        OrganizationsOverviewEvent.Ongoing(
                            overviews[it].ongoing
                        )
                    )
                },
                title = stringResource(R.string.ongoing),
                value = overviews[it].ongoing.size.toString()
            )
            RowItem(
                modifier = Modifier.niceClickable {
                    onEvent(
                        OrganizationsOverviewEvent.Complete(
                            overviews[it].completed
                        )
                    )
                },
                title = stringResource(R.string.completed),
                value = overviews[it].completed.size.toString()
            )
            RowItem(
                modifier = Modifier.niceClickable {
                    onEvent(
                        OrganizationsOverviewEvent.Archived(
                            overviews[it].archived
                        )
                    )
                },
                title = stringResource(R.string.archived),
                value = overviews[it].archived.size.toString()
            )
        }
    }
}