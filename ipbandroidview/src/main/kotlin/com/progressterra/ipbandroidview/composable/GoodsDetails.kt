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
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.model.store.GoodsColor
import com.progressterra.ipbandroidview.model.store.GoodsDetails
import com.progressterra.ipbandroidview.model.store.GoodsParameters
import com.progressterra.ipbandroidview.theme.AppTheme
import kotlinx.coroutines.launch

private val detailsParamTitlesWidth = 104.dp

@OptIn(ExperimentalPagerApi::class)
@Composable
fun GoodsDetails(modifier: Modifier = Modifier, state: GoodsDetails) {

    @Composable
    fun HorizontalTabs(
        pagerState: PagerState
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = AppTheme.dimensions.small)
                .clip(AppTheme.shapes.medium)
                .background(AppTheme.colors.surfaces)
                .padding(AppTheme.dimensions.smany)
        ) {
            TabRow(selectedTabIndex = pagerState.currentPage,
                backgroundColor = AppTheme.colors.surfaces,
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
                        if (selected) AppTheme.colors.background else AppTheme.colors.surfaces
                    Tab(modifier = Modifier.clip(AppTheme.shapes.small),
                        selected = selected,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = index)
                            }
                        }) {
                        val textColor =
                            if (selected) AppTheme.colors.black else AppTheme.colors.gray1
                        val style =
                            if (selected) AppTheme.typography.text else AppTheme.typography.secondaryText
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(AppTheme.shapes.small)
                                .background(backgroundColor)
                                .padding(AppTheme.dimensions.medium),
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

    val pagerState = rememberPagerState()
    Column(
        modifier = modifier, verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
    ) {
        HorizontalTabs(pagerState = pagerState)
        HorizontalPager(
            count = 3,
            state = pagerState,
            verticalAlignment = Alignment.Top,
            contentPadding = PaddingValues(horizontal = AppTheme.dimensions.small),
            itemSpacing = AppTheme.dimensions.small
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(AppTheme.shapes.medium)
                    .background(AppTheme.colors.surfaces)
                    .padding(AppTheme.dimensions.medium),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium)
            ) {
                if (it == 0) {
                    Text(
                        text = state.name,
                        color = AppTheme.colors.black,
                        style = AppTheme.typography.title
                    )
                    Text(
                        text = state.description,
                        color = AppTheme.colors.gray1,
                        style = AppTheme.typography.secondaryText
                    )
                }
                if (it == 1) {
                    Text(
                        text = stringResource(id = R.string.parameters),
                        color = AppTheme.colors.black,
                        style = AppTheme.typography.title
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)) {
                        state.parameters.forEach {
                            Row {
                                Text(
                                    modifier = Modifier.width(detailsParamTitlesWidth),
                                    text = it.title,
                                    color = AppTheme.colors.gray2,
                                    style = AppTheme.typography.secondaryText
                                )
                                Text(
                                    text = it.description,
                                    color = AppTheme.colors.black,
                                    style = AppTheme.typography.secondaryText
                                )
                            }
                        }
                    }

                }
                if (it == 2) {
                    Text(
                        text = stringResource(id = R.string.delivery),
                        color = AppTheme.colors.black,
                        style = AppTheme.typography.title
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun GoodsDetailsPagerPreview() {
    AppTheme {
        GoodsDetails(
            state = GoodsDetails(
                color = "",
                colors = listOf(),
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