package com.progressterra.ipbandroidview.components.bar

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.components.utils.SideBorder
import com.progressterra.ipbandroidview.components.utils.sideBorder
import com.progressterra.ipbandroidview.theme.AppTheme

private val lineWidth = 1.dp

@Composable
fun ThemedBottomAppBar(
    modifier: Modifier = Modifier,
    items: () -> List<BottomMenuTabState>,
    activeIndex: @Composable () -> Int,
    onClick: (Int) -> Unit
) {
    BasicBar(
        modifier = modifier
            .sideBorder(top = SideBorder(lineWidth, AppTheme.colors.gray2))
            .padding(
                top = lineWidth
            )
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