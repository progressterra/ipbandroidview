package com.progressterra.ipbandroidview.shared.ui.bottommenutab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
fun BottomMenuTab(
    modifier: Modifier = Modifier, state: BottomMenuTabState, onClick: (Int) -> Unit, active: Int
) {
    Column(modifier = modifier
        .clip(CircleShape)
        .niceClickable { onClick(state.id) }
        .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)) {
        BrushedIcon(
            modifier = Modifier.size(24.dp),
            resId = state.iconId,
            tint = if (active == state.id) IpbTheme.colors.primary.asBrush() else IpbTheme.colors.iconPrimary.asBrush()
        )
        BrushedText(
            text = stringResource(id = state.titleId),
            style = IpbTheme.typography.footnoteBold,
            tint = if (active == state.id) IpbTheme.colors.primary.asBrush() else IpbTheme.colors.iconPrimary.asBrush()
        )
    }
}

@Preview
@Composable
private fun BottomNavItemPreview() {
    IpbTheme {
        BottomMenuTab(state = BottomMenuTabState(
            id = 0,
            iconId = R.drawable.ic_profile,
            titleId = R.string.address,
        ), active = 0, onClick = {})
    }
}