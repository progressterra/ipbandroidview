package com.progressterra.ipbandroidview.composable.bottomnav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun BottomNavItem(
    modifier: Modifier = Modifier,
    gap: Dp = 4.dp,
    padding: Dp = 4.dp,
    countSize: Dp = 13.dp,
    countRadius: Dp = 100.dp,
    state: BottomNavItemState
) {
    Column(
        modifier = modifier.padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.TopEnd) {
            if (state.count > 0) {
                Box(
                    modifier = Modifier
                        .size(countSize)
                        .clip(RoundedCornerShape(countRadius))
                        .background(AppTheme.colors.primary)
                        .zIndex(1f),
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
                painter = painterResource(id = state.iconId),
                contentDescription = "${stringResource(id = R.string.icon)} ${state.title}",
                tint = if (state.active) AppTheme.colors.primary else AppTheme.colors.gray2
            )
        }
        if (state.title.isNotBlank()) {
            Spacer(modifier = Modifier.size(gap))
            Text(
                text = state.title,
                style = AppTheme.typography.actionBarLabels,
                color = if (state.active) AppTheme.colors.primary else AppTheme.colors.gray2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomNavItemPreview0() {
    AppTheme {
        BottomNavItem(
            state = BottomNavItemState(
                iconId = R.drawable.ic_profile,
                active = true,
                count = 3,
                title = "Profile"
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomNavItemPreview1() {
    AppTheme {
        BottomNavItem(
            state = BottomNavItemState(
                iconId = R.drawable.ic_audits,
                active = true,
                count = 0,
                title = "Audits"
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomNavItemPreview2() {
    AppTheme {
        BottomNavItem(
            state = BottomNavItemState(
                iconId = R.drawable.ic_organization,
                active = false,
                count = 0
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomNavItemPreview3() {
    AppTheme {
        BottomNavItem(
            state = BottomNavItemState(
                iconId = R.drawable.ic_audits,
                active = false,
                count = 12
            )
        )
    }
}