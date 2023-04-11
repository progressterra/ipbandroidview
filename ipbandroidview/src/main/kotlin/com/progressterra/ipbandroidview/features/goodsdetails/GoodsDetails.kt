package com.progressterra.ipbandroidview.features.goodsdetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.niceClickable
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GoodsDetails(modifier: Modifier = Modifier, state: GoodsDetailsState) {
    val pagerState = rememberPagerState()
    val tabs = listOf(
        stringResource(id = R.string.description),
        stringResource(id = R.string.parameters),
        stringResource(id = R.string.delivery)
    )
    Column(
        modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(IpbTheme.colors.surface.asBrush())
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val scope = rememberCoroutineScope()
            tabs.forEachIndexed { index, text ->
                val selected = pagerState.currentPage == index
                val backgroundColor =
                    if (selected) IpbTheme.colors.background else IpbTheme.colors.surface
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(backgroundColor.asBrush())
                        .niceClickable { scope.launch { pagerState.animateScrollToPage(page = index) } },
                    contentAlignment = Alignment.Center
                ) {
                    val textColor =
                        if (selected) IpbTheme.colors.textPressed else IpbTheme.colors.textSecondary
                    val style =
                        if (selected) IpbTheme.typography.subHeadlineBold else IpbTheme.typography.subHeadlineRegular
                    BrushedText(
                        text = text,
                        tint = textColor.asBrush(),
                        style = style,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        HorizontalPager(
            state = pagerState,
            pageCount = tabs.size
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(IpbTheme.colors.surface.asBrush())
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (it == 0) {
                    BrushedText(
                        text = state.name,
                        tint = IpbTheme.colors.textPrimary.asBrush(),
                        style = IpbTheme.typography.title
                    )
                    BrushedText(
                        text = state.company,
                        tint = IpbTheme.colors.textTertiary.asBrush(),
                        style = IpbTheme.typography.subHeadlineItalic
                    )
                    BrushedText(
                        text = state.description,
                        tint = IpbTheme.colors.textSecondary.asBrush(),
                        style = IpbTheme.typography.subHeadlineRegular
                    )
                }
            }
        }
    }
}