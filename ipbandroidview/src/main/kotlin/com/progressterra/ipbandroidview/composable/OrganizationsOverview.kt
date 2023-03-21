package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.ui.niceClickable
import com.progressterra.ipbandroidview.model.Document
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
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
            style = IpbTheme.typography.secondaryText,
            color = IpbTheme.colors.gray1,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = value, style = IpbTheme.typography.text, color = IpbTheme.colors.black
        )
        Spacer(modifier = Modifier.width(16.dp))
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
            .clip(IpbTheme.shapes.medium)
            .background(IpbTheme.colors.surfaces)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.audits),
            style = IpbTheme.typography.text,
            color = IpbTheme.colors.black
        )
        val pagerState = rememberPagerState()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val scope = rememberCoroutineScope()
            overviews.forEachIndexed { index, overview ->
                val selected = pagerState.currentPage == index
                val backgroundColor =
                    if (selected) IpbTheme.colors.background else IpbTheme.colors.surfaces

                val textColor = if (selected) IpbTheme.colors.black else IpbTheme.colors.gray1
                val style =
                    if (selected) IpbTheme.typography.text else IpbTheme.typography.secondaryText
                Box(modifier = Modifier
                    .clip(IpbTheme.shapes.small)
                    .border(
                        width = borderWidth,
                        color = IpbTheme.colors.background,
                        shape = IpbTheme.shapes.small
                    )
                    .background(backgroundColor)
                    .niceClickable {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                    .padding(
                        vertical = 8.dp, horizontal = tabHorizontalPadding
                    ), contentAlignment = Alignment.Center) {
                    Text(
                        text = overview.name,
                        color = textColor,
                        style = style,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        HorizontalPager(
            count = overviews.size, state = pagerState
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
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
}

@Composable
@Preview
private fun OrganizationsOverviewPreview() {
    IpbTheme {
        OrganizationsOverview(
            overviews = listOf(
                OrganizationOverview(
                    name = "Organization 1",
                    ongoing = listOf(),
                    completed = listOf(),
                    archived = listOf()
                ), OrganizationOverview(
                    name = "Organization 2",
                    ongoing = listOf(),
                    completed = listOf(),
                    archived = listOf()
                ), OrganizationOverview(
                    name = "Organization 3",
                    ongoing = listOf(),
                    completed = listOf(),
                    archived = listOf()
                )
            )
        ) {}
    }
}