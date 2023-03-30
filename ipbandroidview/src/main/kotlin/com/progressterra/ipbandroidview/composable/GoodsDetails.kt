package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
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
import com.google.accompanist.pager.PagerScope
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.GoodsItem
import com.progressterra.ipbandroidview.entities.GoodsParameters
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import kotlinx.coroutines.launch

private val detailsParamTitlesWidth = 104.dp

@OptIn(ExperimentalPagerApi::class)
@Composable
fun GoodsDetails(modifier: Modifier = Modifier, state: GoodsItem) {

    @Composable
    fun HorizontalTabs(
        pagerState: PagerState
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clip(IpbTheme.shapes.medium)
                .background(IpbTheme.colors.surfaces)
                .padding(6.dp)
        ) {
            TabRow(selectedTabIndex = pagerState.currentPage,
                backgroundColor = IpbTheme.colors.surfaces,
                indicator = {},
                divider = {}) {
                val scope = rememberCoroutineScope()
                listOf(
                    stringResource(id = R.string.description),
                    stringResource(id = R.string.parameters),
                    stringResource(id = R.string.delivery)
                ).forEachIndexed { index, text ->
                    val selected = pagerState.currentPage == index
                    val backgroundColor =
                        if (selected) IpbTheme.colors.background else IpbTheme.colors.surfaces
                    Tab(modifier = Modifier.clip(IpbTheme.shapes.small),
                        selected = selected,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = index)
                            }
                        }) {
                        val textColor =
                            if (selected) IpbTheme.colors.black else IpbTheme.colors.gray1
                        val style =
                            if (selected) IpbTheme.typography.primary else IpbTheme.typography.secondary
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(IpbTheme.shapes.small)
                                .background(backgroundColor)
                                .padding(12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = text,
                                color = textColor,
                                style = style,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }

    val pagerState = androidx.compose.foundation.pager.rememberPagerState()
    Column(
        modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HorizontalTabs(pagerState = pagerState)
        PaddingValues(horizontal = 8.dp)
        PagerDefaults.flingBehavior(
            state = state,
            endContentPadding = contentPadding.calculateEndPadding(LayoutDirection.Ltr),
        )
        fun PagerScope.(it: Int) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(IpbTheme.shapes.medium)
                    .background(IpbTheme.colors.surfaces)
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (it == 0) {
                    Text(
                        text = state.name,
                        color = IpbTheme.colors.black,
                        style = IpbTheme.typography.title
                    )
                    Text(
                        text = state.description,
                        color = IpbTheme.colors.gray1,
                        style = IpbTheme.typography.secondary
                    )
                }
                if (it == 1) {
                    Text(
                        text = stringResource(id = R.string.parameters),
                        color = IpbTheme.colors.black,
                        style = IpbTheme.typography.title
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        state.parameters.forEach {
                            Row {
                                Text(
                                    modifier = Modifier.width(detailsParamTitlesWidth),
                                    text = it.title,
                                    color = IpbTheme.colors.gray2,
                                    style = IpbTheme.typography.secondary
                                )
                                Text(
                                    text = it.description,
                                    color = IpbTheme.colors.black,
                                    style = IpbTheme.typography.secondary
                                )
                            }
                        }
                    }

                }
                if (it == 2) {
                    Text(
                        text = stringResource(id = R.string.delivery),
                        color = IpbTheme.colors.black,
                        style = IpbTheme.typography.title
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun GoodsDetailsPagerPreview() {
    IpbTheme {
        GoodsDetails(
            state = GoodsItem(
                description = "Гидрокостюм Dawn Patrol с молнией на груди отличается функциональностью и отличным теплосбережением, красивым ...",
                favorite = false,
                images = listOf(),
                inCartCounter = 0,
                name = "SUPER COOL GOOD",
                parameters = listOf(
                    GoodsParameters(
                        title = "Цвет", description = "BEZHEVIY"
                    ), GoodsParameters(
                        title = "Цвет", description = "MaGenta"
                    ), GoodsParameters(
                        title = "Цвет", description = "CMYK"
                    )
                )
            )
        )
    }
}