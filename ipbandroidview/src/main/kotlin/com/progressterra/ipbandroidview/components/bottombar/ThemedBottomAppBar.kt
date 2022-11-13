package com.progressterra.ipbandroidview.components.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.components.utils.SideBorder
import com.progressterra.ipbandroidview.components.utils.sideBorder
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ThemedBottomAppBar(
    modifier: Modifier = Modifier,
    items: () -> List<BottomMenuTabState>,
    activeIndex: () -> Int,
    onClick: (Int) -> Unit
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
        items().forEachIndexed { index, item ->
            BottomMenuTab(
                modifier = modifier.weight(1f),
                state = { item },
                active = { index == activeIndex() },
                onClick = { onClick(index) })
        }
    }
}

@Preview
@Composable
private fun BottomNavPreview() {
    AppTheme {
    }
}