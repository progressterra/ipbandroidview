package com.progressterra.ipbandroidview.components.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.utils.SideBorder
import com.progressterra.ipbandroidview.components.utils.sideBorder
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ThemedBottomAppBar(
    modifier: Modifier = Modifier, items: List<BottomMenuItem>, activeIndex: Int
) {
    BottomAppBar(
        modifier = modifier
            .fillMaxWidth()
            .background(AppTheme.colors.surfaces)
            .sideBorder(top = SideBorder(1.dp, AppTheme.colors.gray2))
            .padding(top = 1.dp),
        backgroundColor = AppTheme.colors.surfaces,
        elevation = 0.dp
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuTab(
                modifier = modifier.weight(1f), state = item, active = index == activeIndex
            )
        }
    }
}

@Preview
@Composable
private fun BottomNavPreview() {
    AppTheme {
        ThemedBottomAppBar(
            items = listOf(
                BottomMenuItem(iconId = R.drawable.ic_organization,
                    count = 12,
                    titleId = R.string.address,
                    onClick = {}), BottomMenuItem(iconId = R.drawable.ic_audits,
                    count = 0,
                    titleId = R.string.address,
                    onClick = {}), BottomMenuItem(iconId = R.drawable.ic_profile,
                    count = 3,
                    titleId = R.string.address,
                    onClick = {})
            ), activeIndex = 0
        )
    }
}