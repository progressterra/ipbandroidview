package com.progressterra.ipbandroidview.features.bottombar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.stealthClickable

@Composable
fun RediBottomBar(
    modifier: Modifier = Modifier,
    state: RediBottomBarState,
    useComponent: UseRediBottomBar
) {

    @Composable
    fun Item(
        iconRes: Int,
        titleRes: Int,
        counter: Int = 0,
        index: Int
    ) {
        Column(
            modifier = Modifier
                .stealthClickable { useComponent.handle(RediBottomBarEvent(index)) },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = Modifier.size(24.dp)
            ) {
                BrushedIcon(
                    modifier = Modifier.size(24.dp),
                    resId = iconRes,
                    tint = if (index == state.activeIndex) IpbTheme.colors.primary.asBrush() else IpbTheme.colors.iconPrimary.asBrush()
                )
                if (counter > 0) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .clip(CircleShape)
                            .background(IpbTheme.colors.primary.asBrush())
                            .padding(horizontal = 2.dp)
                    ) {
                        BrushedText(
                            text = counter.toString(),
                            style = IpbTheme.typography.caption,
                            tint = IpbTheme.colors.surface.asBrush()
                        )
                    }
                }
            }
            BrushedText(
                text = stringResource(id = titleRes),
                style = IpbTheme.typography.footnoteBold,
                tint = if (index == state.activeIndex) IpbTheme.colors.primary.asBrush() else IpbTheme.colors.iconPrimary.asBrush()
            )
        }
    }
    Box(modifier = modifier.height(85.dp), contentAlignment = Alignment.BottomCenter) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp),
            painter = painterResource(id = R.drawable.ic_bottom_bar),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Item(
                iconRes = R.drawable.ic_main,
                titleRes = R.string.main_screen,
                index = 0
            )
            Item(
                iconRes = R.drawable.ic_catalog,
                titleRes = R.string.catalog_screen,
                index = 1
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .stealthClickable { useComponent.handle(RediBottomBarEvent(2)) },
                    contentAlignment = Alignment.Center
                ) {
                    val image =
                        if (2 == state.activeIndex) R.drawable.ic_want_this_on else R.drawable.ic_want_this_off
                    Image(
                        modifier = Modifier.size(48.dp),
                        painter = painterResource(id = image),
                        contentDescription = null
                    )
                }
                BrushedText(
                    text = stringResource(id = R.string.want_this),
                    style = IpbTheme.typography.footnoteBold,
                    tint = if (2 == state.activeIndex) IpbTheme.colors.primary.asBrush() else IpbTheme.colors.iconPrimary.asBrush()
                )
            }
            Item(
                iconRes = R.drawable.ic_cart,
                titleRes = R.string.cart_screen,
                counter = state.cartCounter,
                index = 3
            )
            Item(
                iconRes = R.drawable.ic_profile,
                titleRes = R.string.profile_screen,
                index = 4
            )
        }
    }
}

@Preview
@Composable
private fun RediBottomBarPreview() {
    IpbTheme {
        RediBottomBar(
            state = RediBottomBarState(
                activeIndex = 1,
                cartCounter = 1
            ), useComponent = UseRediBottomBar.Empty()
        )
    }
}

@Preview
@Composable
private fun RediBottomBarPreviewWantThis() {
    IpbTheme {
        RediBottomBar(
            state = RediBottomBarState(
                activeIndex = 2
            ), useComponent = UseRediBottomBar.Empty()
        )
    }
}