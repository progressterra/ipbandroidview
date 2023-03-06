package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.ui.SideBorder
import com.progressterra.ipbandroidview.shared.ui.sideBorder
import com.progressterra.ipbandroidview.theme.AppTheme

private val lineWidth = 1.dp

@Composable
fun ThemedBottomAppBar(
    modifier: Modifier = Modifier,
    items: List<BottomMenuTabState>,
    activeIndex: Int,
    onClick: (Int) -> Unit
) {
    BasicBar(
        modifier = modifier
            .sideBorder(top = SideBorder(lineWidth, AppTheme.colors.gray2))
            .padding(
                top = lineWidth
            ),
        paddingValues = PaddingValues(horizontal = AppTheme.dimensions.medium),
        arrangement = Arrangement.SpaceEvenly
    ) {
        items.forEach { item ->
            BottomMenuTab(
                modifier = modifier.weight(1f),
                state = item,
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
        ThemedBottomAppBar(
            items = listOf(
                BottomMenuTabState(
                    id = 0,
                    count = 0,
                    titleId = R.string.audit,
                    iconId = R.drawable.ic_audits
                ),
                BottomMenuTabState(
                    id = 0,
                    count = 0,
                    titleId = R.string.audit,
                    iconId = R.drawable.ic_audits
                ),
                BottomMenuTabState(
                    id = 0,
                    count = 0,
                    titleId = R.string.audit,
                    iconId = R.drawable.ic_audits
                )
            ), activeIndex = 1,
            onClick = {}
        )
    }
}