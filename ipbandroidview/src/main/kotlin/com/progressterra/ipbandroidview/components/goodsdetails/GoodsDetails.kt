package com.progressterra.ipbandroidview.components.goodsdetails

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.progressterra.ipbandroidview.dto.GoodsParameters
import com.progressterra.ipbandroidview.dto.component.Description
import com.progressterra.ipbandroidview.dto.component.Name
import com.progressterra.ipbandroidview.dto.component.Parameters
import com.progressterra.ipbandroidview.theme.AppTheme
import kotlinx.coroutines.launch


interface GoodsDetailsState : Name, Parameters, Description

@OptIn(ExperimentalPagerApi::class)
@Composable
fun GoodsDetails(modifier: Modifier = Modifier, state: GoodsDetailsState) {

    @Composable
    fun HorizontalTabs(
        pagerState: PagerState
    ) {
        Box(
            modifier = Modifier
                .clip(AppTheme.shapes.medium)
                .background(AppTheme.colors.surfaces)
                .padding(6.dp)
        ) {
            TabRow(selectedTabIndex = pagerState.currentPage,
                backgroundColor = AppTheme.colors.surfaces,
                indicator = {},
                divider = {}) {
                val scope = rememberCoroutineScope()
                for (i in 0..2) {
                    val selected = pagerState.currentPage == i
                    val backgroundColor =
                        if (selected) AppTheme.colors.background else AppTheme.colors.surfaces
                    Tab(modifier = Modifier.clip(AppTheme.shapes.small),
                        selected = selected,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = i)
                                Log.d("SCROLL", "HorizontalTabs: $i")
                            }
                        }) {
                        val text = when (i) {
                            0 -> stringResource(id = R.string.description)
                            1 -> stringResource(id = R.string.parameters)
                            else -> stringResource(id = R.string.delivery)
                        }
                        val textColor =
                            if (selected) AppTheme.colors.black else AppTheme.colors.gray1
                        val style =
                            if (selected) AppTheme.typography.text else AppTheme.typography.secondaryText
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(backgroundColor)
                                .padding(AppTheme.dimensions.large),
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

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium)
    ) {
        val pagerState = rememberPagerState()
        HorizontalTabs(pagerState = pagerState)
        HorizontalPager(count = 3) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(AppTheme.shapes.medium)
                    .background(AppTheme.colors.surfaces)
                    .padding(AppTheme.dimensions.large),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.large)
            ) {
                val text = when (it) {
                    0 -> state.name
                    1 -> stringResource(id = R.string.parameters)
                    else -> stringResource(id = R.string.delivery)
                }
                Text(
                    text = text, color = AppTheme.colors.black, style = AppTheme.typography.title
                )
                when (it) {
                    0 -> Text(
                        text = state.description,
                        color = AppTheme.colors.gray1,
                        style = AppTheme.typography.secondaryText
                    )
                    1 -> LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium)
                    ) {
                        items(state.parameters) {
                            Row {
                                Text(
                                    modifier = Modifier.width(104.dp),
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
                    else -> {}
                }
            }
        }
    }
}

private class GoodsDetailsStatePreview : GoodsDetailsState {

    override val description: String =
        "Гидрокостюм Dawn Patrol с молнией на груди отличается функциональностью и отличным теплосбережением, красивым ..."

    override val name: String = "SUPER COOL GOOD"

    override val parameters: List<GoodsParameters> = listOf(
        GoodsParameters(
            title = "Цвет", description = "BEZHEVIY"
        ), GoodsParameters(
            title = "Цвет", description = "MaGenta"
        ), GoodsParameters(
            title = "Цвет", description = "CMYK"
        )
    )
}

@Preview
@Composable
private fun GoodsDetailsPagerPreview() {
    AppTheme {
        GoodsDetails(
            state = GoodsDetailsStatePreview()
        )
    }
}