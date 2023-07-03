package com.progressterra.ipbandroidview.features.goodsdescription

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
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
import com.progressterra.ipbandroidview.features.favoritebutton.FavoriteButton
import com.progressterra.ipbandroidview.features.favoritebutton.FavoriteButtonState
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.niceClickable
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GoodsDescription(
    modifier: Modifier = Modifier, state: GoodsDescriptionState, useComponent: UseGoodsDescription
) {
    val pagerState = rememberPagerState()
    val tabs = listOf(
        stringResource(id = R.string.description),
        stringResource(id = R.string.parameters),
        stringResource(id = R.string.delivery)
    )
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
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
                Box(modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(backgroundColor.asBrush())
                    .niceClickable { scope.launch { pagerState.animateScrollToPage(page = index) } }
                    .padding(vertical = 12.dp), contentAlignment = Alignment.Center) {
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
            state = pagerState, pageCount = tabs.size,
            contentPadding = PaddingValues(horizontal = 20.dp),
            pageSpacing = 20.dp,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(IpbTheme.colors.surface.asBrush())
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                when (it) {
                    0 -> {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            BrushedText(
                                modifier = Modifier.widthIn(max = 200.dp),
                                text = state.name,
                                tint = IpbTheme.colors.textPrimary.asBrush(),
                                style = IpbTheme.typography.title
                            )
                            Spacer(Modifier.weight(1f))
                            FavoriteButton(
                                state = state.favoriteButton, useComponent = useComponent
                            )
                            Spacer(Modifier.width(20.dp))
                            IconButton(
                                modifier = Modifier.size(24.dp),
                                onClick = { useComponent.handle(GoodsDescriptionEvent.Share) }) {
                                BrushedIcon(
                                    resId = R.drawable.ic_share,
                                    tint = IpbTheme.colors.iconTertiary.asBrush()
                                )
                            }
                        }
                        BrushedText(
                            text = state.description,
                            tint = IpbTheme.colors.textSecondary.asBrush(),
                            style = IpbTheme.typography.subHeadlineRegular
                        )
                    }

                    1 -> {
                        BrushedText(
                            text = stringResource(R.string.parameters),
                            tint = IpbTheme.colors.textPrimary.asBrush(),
                            style = IpbTheme.typography.title
                        )
                        state.properties.forEach {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                BrushedText(
                                    modifier = Modifier.width(100.dp),
                                    text = it.key,
                                    tint = IpbTheme.colors.textSecondary.asBrush(),
                                    style = IpbTheme.typography.subHeadlineRegular
                                )
                                BrushedText(
                                    text = it.value,
                                    tint = IpbTheme.colors.textPrimary.asBrush(),
                                    style = IpbTheme.typography.subHeadlineRegular
                                )
                            }
                        }
                    }

                    2 -> {
                        BrushedText(
                            text = stringResource(R.string.delivery),
                            tint = IpbTheme.colors.textPrimary.asBrush(),
                            style = IpbTheme.typography.title
                        )
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            BrushedIcon(
                                modifier = Modifier.size(45.dp),
                                resId = R.drawable.ic_courier,
                                tint = IpbTheme.colors.iconPrimary3.asBrush()
                            )
                            BrushedText(
                                text = stringResource(R.string.courier_delivery),
                                tint = IpbTheme.colors.textPrimary.asBrush(),
                                style = IpbTheme.typography.body
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewGoodsDescription() {
    GoodsDescription(
        state = GoodsDescriptionState(
            name = "Product Loooooooooooooooooooooooong Name",
            description = "This is a great product that you would definitely want to buy.",
            favoriteButton = FavoriteButtonState(
                id = "1", enabled = true, favorite = false
            ),
            properties = mapOf(
                "color" to "black",
                "size" to "M"
            )
        ), useComponent = UseGoodsDescription.Empty()
    )
}
