package com.progressterra.ipbandroidview.composable.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun BottomMenuTab(
    modifier: Modifier = Modifier,
    state: BottomMenuItem,
    active: Boolean
) {
    Column(modifier = modifier
        .clickable {
            state.onClick()
        }
        .padding(4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.size(24.dp)) {
            if (state.count > 0) {
                Box(
                    modifier = Modifier
                        .size(13.dp)
                        .clip(CircleShape)
                        .background(AppTheme.colors.primary)
                        .zIndex(1f).align(Alignment.TopEnd),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.count.toString(),
                        textAlign = TextAlign.Center,
                        color = AppTheme.colors.surfaces,
                        style = AppTheme.typography.actionBarLabels
                    )
                }
            }
            Icon(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(id = state.iconId),
                contentDescription = "${stringResource(id = R.string.icon)} ${stringResource(id = state.titleId)}",
                tint = if (active) AppTheme.colors.primary else AppTheme.colors.gray2
            )
        }
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = stringResource(id = state.titleId),
            style = AppTheme.typography.actionBarLabels,
            color = if (active) AppTheme.colors.primary else AppTheme.colors.gray2
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomNavItemPreview0() {
    AppTheme {
        BottomMenuTab(
            state = BottomMenuItem(
                iconId = R.drawable.ic_profile,
                count = 3,
                titleId = R.string.address,
                onClick = {}), active = true
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomNavItemPreview1() {
    AppTheme {
        BottomMenuTab(
            state = BottomMenuItem(
                iconId = R.drawable.ic_audits,
                count = 0,
                titleId = R.string.address,
                onClick = {}), active = false
        )
    }
}