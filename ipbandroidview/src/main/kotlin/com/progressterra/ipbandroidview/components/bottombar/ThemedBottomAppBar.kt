package com.progressterra.ipbandroidview.components.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.components.utils.SideBorder
import com.progressterra.ipbandroidview.components.utils.sideBorder
import com.progressterra.ipbandroidview.theme.AppTheme

private val lineWidth = 1.dp

private val elevation = 0.dp

@Composable
fun ThemedBottomAppBar(
    modifier: Modifier = Modifier,
    items: () -> List<BottomMenuTabState>,
    activeIndex: @Composable () -> Int,
    onClick: (Int) -> Unit
) {
    BottomAppBar(
        modifier = modifier
            .background(AppTheme.colors.surfaces)
            .sideBorder(top = SideBorder(lineWidth, AppTheme.colors.gray2))
            .padding(top = lineWidth),
        backgroundColor = AppTheme.colors.surfaces,
        elevation = elevation
    ) {
        items().forEach { item ->
            BottomMenuTab(
                modifier = modifier.weight(1f),
                state = { item },
                active = activeIndex,
                onClick = onClick
            )
        }
    }
}

@Preview
@Composable
private fun BottomNavPreview() {
    AppTheme {
    }
}