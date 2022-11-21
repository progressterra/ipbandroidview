package com.progressterra.ipbandroidview.composable.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
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

private val bottomMenuIconSize = 32.dp

@Immutable
data class BottomMenuTabState(
    val id: Int,
    val count: Int = 0,
    @StringRes val titleId: Int,
    @DrawableRes val iconId: Int
)

@Composable
fun BottomMenuTab(
    modifier: Modifier = Modifier,
    state: () -> BottomMenuTabState,
    onClick: (Int) -> Unit,
    active: @Composable () -> Int
) {

    @Composable
    fun Counter(modifier: Modifier = Modifier, count: () -> Int) {
        if (count() > 0) {
            Box(
                modifier = modifier
                    .size(AppTheme.dimensions.counterSize)
                    .clip(CircleShape)
                    .background(AppTheme.colors.secondary)
                    .zIndex(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state().count.toString(),
                    textAlign = TextAlign.Center,
                    color = AppTheme.colors.black,
                    style = AppTheme.typography.actionBarLabels
                )
            }
        }
    }

    Column(modifier = modifier
        .clickable { onClick(state().id) }
        .padding(AppTheme.dimensions.tiny),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiny)
    ) {
        Box(modifier = Modifier.size(bottomMenuIconSize)) {
            Counter(modifier = Modifier.align(Alignment.TopEnd), count = state()::count)
            Icon(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(id = state().iconId),
                contentDescription = null,
                tint = if (active() == state().id) AppTheme.colors.primary else AppTheme.colors.gray2
            )
        }
        Text(
            text = stringResource(id = state().titleId),
            style = AppTheme.typography.actionBarLabels,
            color = if (active() == state().id) AppTheme.colors.primary else AppTheme.colors.gray2
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomNavItemPreview0() {
    AppTheme {
        BottomMenuTab(
            state = {
                BottomMenuTabState(
                    id = 0,
                    iconId = R.drawable.ic_profile,
                    count = 3,
                    titleId = R.string.address,
                )
            }, active = { 0 }, onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomNavItemPreview1() {
    AppTheme {
        BottomMenuTab(
            state = {
                BottomMenuTabState(
                    id = 0,
                    iconId = R.drawable.ic_audits,
                    count = 0,
                    titleId = R.string.address,
                )
            }, active = { 1 }, onClick = {})
    }
}