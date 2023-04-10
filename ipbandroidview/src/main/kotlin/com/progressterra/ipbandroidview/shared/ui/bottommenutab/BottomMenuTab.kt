package com.progressterra.ipbandroidview.shared.ui.bottommenutab

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Composable
fun BottomMenuTab(
    modifier: Modifier = Modifier,
    state: BottomMenuTabState,
    onClick: (Int) -> Unit,
    active: Int
) {

    @Composable
    fun Counter(modifier: Modifier = Modifier, count: Int) {
        if (count > 0) {
            Box(
                modifier = modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(IpbTheme.colors.primary.asBrush())
                    .zIndex(1f),
                contentAlignment = Alignment.Center
            ) {
                BrushedText(
                    text = state.count.toString(),
                    textAlign = TextAlign.Center,
                    tint = IpbTheme.colors.textPrimary1.asBrush(),
                    style = IpbTheme.typography.footnoteBold
                )
            }
        }
    }

    Column(modifier = modifier
        .clickable { onClick(state.id) }
        .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(modifier = Modifier.size(32.dp)) {
            Counter(modifier = Modifier.align(Alignment.TopEnd), count = state.count)
            BrushedIcon(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center),
                resId = state.iconId,
                tint = if (active == state.id) IpbTheme.colors.primary.asBrush() else IpbTheme.colors.iconPrimary1.asBrush()
            )
        }
        BrushedText(
            text = stringResource(id = state.titleId),
            style = IpbTheme.typography.footnoteBold,
            tint = if (active == state.id) IpbTheme.colors.primary.asBrush() else IpbTheme.colors.iconPrimary1.asBrush()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomNavItemPreview0() {
    IpbTheme {
        BottomMenuTab(
            state = BottomMenuTabState(
                id = 0,
                iconId = R.drawable.ic_profile,
                count = 3,
                titleId = R.string.address,
            ), active = 0, onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomNavItemPreview1() {
    IpbTheme {
        BottomMenuTab(
            state = BottomMenuTabState(
                id = 0,
                iconId = R.drawable.ic_audits,
                count = 0,
                titleId = R.string.address,
            ), active = 1, onClick = {})
    }
}