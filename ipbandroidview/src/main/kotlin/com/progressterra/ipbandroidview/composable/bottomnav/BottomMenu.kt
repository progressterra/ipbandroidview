package com.progressterra.ipbandroidview.composable.bottomnav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.Border
import com.progressterra.ipbandroidview.composable.border
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun BottomMenu(
    modifier: Modifier = Modifier, items: List<BottomMenuItem>
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(AppTheme.colors.surfaces)
            .border(top = Border(1.dp, AppTheme.colors.gray2))
            .padding(top = 1.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach {
            BottomMenuTab(modifier = modifier.weight(1f), state = it)
        }
    }
}

@Preview
@Composable
private fun BottomNavPreview() {
    AppTheme {
        BottomMenu(
            items = listOf(
                BottomMenuItem(
                    iconId = R.drawable.ic_organization,
                    active = false,
                    count = 12,
                    title = "Organizations"
                ), BottomMenuItem(
                    iconId = R.drawable.ic_audits, active = true, count = 0, title = "Audits"
                ), BottomMenuItem(
                    iconId = R.drawable.ic_profile, active = false, count = 3, title = "Profile"
                )

            )
        )
    }
}