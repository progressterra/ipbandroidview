package com.progressterra.ipbandroidview.features.goodsdescription

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.favoritebutton.FavoriteButton
import com.progressterra.ipbandroidview.features.favoritebutton.FavoriteButtonState
import com.progressterra.ipbandroidview.shared.Tabs
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GoodsDescription(
    modifier: Modifier = Modifier, state: GoodsDescriptionState, useComponent: UseGoodsDescription
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val pagerState = rememberPagerState { 3 }
        val scope = rememberCoroutineScope()
        Tabs(
            modifier = Modifier.padding(horizontal = 20.dp),
            tabs = listOf(
                stringResource(id = R.string.description),
                stringResource(id = R.string.parameters),
                stringResource(id = R.string.delivery)
            ),
            currentIndex = pagerState.currentPage,
            onTabClicked = { scope.launch { pagerState.animateScrollToPage(it) } })
        HorizontalPager(
            state = pagerState,
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
                                    text = it.first,
                                    tint = IpbTheme.colors.textSecondary.asBrush(),
                                    style = IpbTheme.typography.subHeadlineRegular
                                )
                                BrushedText(
                                    text = it.second,
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
                                tint = IpbTheme.colors.iconPrimary.asBrush()
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
            properties = listOf(
                "color" to "black",
                "size" to "M",
                "additional info" to "Veeeeeeeeryyyyy long value it is realy very long it even can't be fitted in field"
            )
        ), useComponent = UseGoodsDescription.Empty()
    )
}
