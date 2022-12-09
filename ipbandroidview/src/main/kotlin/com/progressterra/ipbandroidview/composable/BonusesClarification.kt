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
import com.progressterra.ipbandroidview.composable.ForwardTinyIcon
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun BonusesClarification(
    modifier: Modifier = Modifier,
    burningDate: () -> String,
    burningQuantity: () -> Int,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .niceClickable(onClick = onClick)
            .padding(AppTheme.dimensions.medium),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
    ) {
        Text(
            text = "🔥",
            style = AppTheme.typography.headLine,
            color = AppTheme.colors.black
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiniest)
        ) {
            Text(
                text = "${burningDate()} ${stringResource(R.string.will_burn)} ${burningQuantity()} ${
                    stringResource(R.string.bonuses)
                }",
                style = AppTheme.typography.text,
                color = AppTheme.colors.black
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiniest)
            ) {
                Text(
                    text = stringResource(R.string.to_bonuses_clarification),
                    style = AppTheme.typography.tertiaryText,
                    color = AppTheme.colors.gray2
                )
                ForwardTinyIcon()
            }
        }
    }
}