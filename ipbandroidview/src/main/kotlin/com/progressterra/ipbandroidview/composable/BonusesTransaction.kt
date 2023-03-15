package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.progressterra.ipbandroidview.model.Transaction
import com.progressterra.ipbandroidview.theme.IpbTheme

@Composable
fun BonusesTransaction(modifier: Modifier = Modifier, state: Transaction) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(IpbTheme.shapes.medium)
            .background(IpbTheme.colors.surfaces)
            .padding(vertical = 8.dp, horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(
                text = state.date,
                style = IpbTheme.typography.actionBarLabels,
                color = IpbTheme.colors.gray2
            )
            Text(
                text = state.name,
                style = IpbTheme.typography.text,
                color = IpbTheme.colors.gray1
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (state.delta >= 0)
                Text(
                    text = "+${state.delta}",
                    style = IpbTheme.typography.text,
                    color = IpbTheme.colors.primary
                )
            else
                Text(
                    text = state.delta.toString(),
                    style = IpbTheme.typography.text,
                    color = IpbTheme.colors.error
                )
            BonusesSmallIcon()
        }
    }
}