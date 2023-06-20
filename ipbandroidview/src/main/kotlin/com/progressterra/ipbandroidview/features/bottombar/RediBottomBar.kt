package com.progressterra.ipbandroidview.features.bottombar

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.niceClickable

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
        index: Int
    ) {
        Column(modifier = Modifier
            .clip(CircleShape)
            .niceClickable { useComponent.handle(RediBottomBarEvent.Activate(index)) }
            .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)) {
            BrushedIcon(
                modifier = Modifier.size(24.dp),
                resId = iconRes,
                tint = if (index == state.activeIndex) IpbTheme.colors.primary.asBrush() else IpbTheme.colors.iconPrimary.asBrush()
            )
            BrushedText(
                text = stringResource(id = titleRes),
                style = IpbTheme.typography.footnoteBold,
                tint = if (index == state.activeIndex) IpbTheme.colors.primary.asBrush() else IpbTheme.colors.iconPrimary.asBrush()
            )
        }
    }

    Box(modifier = modifier, contentAlignment = Alignment.BottomCenter) {
        Box(
            modifier = Modifier
                .height(50.dp)
                .background(IpbTheme.colors.surface.asBrush())
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Item(
                iconRes = R.drawable
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
                activeIndex = 1
            ), useComponent = UseRediBottomBar.Empty()
        )
    }
}