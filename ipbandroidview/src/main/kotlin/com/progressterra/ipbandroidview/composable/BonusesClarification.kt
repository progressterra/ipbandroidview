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
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.ui.niceClickable
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun BonusesClarification(
    modifier: Modifier = Modifier, burningDate: String, burningQuantity: String, onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(IpbTheme.shapes.medium)
            .background(IpbTheme.colors.surfaces)
            .niceClickable { onClick() }
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "🔥", style = IpbTheme.typography.headLine, color = IpbTheme.colors.black
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = "$burningDate ${stringResource(R.string.will_burn)} $burningQuantity ${
                    stringResource(R.string.bonuses)
                }", style = IpbTheme.typography.primary, color = IpbTheme.colors.black
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = stringResource(R.string.to_bonuses_clarification),
                    style = IpbTheme.typography.tertiary,
                    color = IpbTheme.colors.gray2
                )
                ForwardTinyIcon()
            }
        }
    }
}