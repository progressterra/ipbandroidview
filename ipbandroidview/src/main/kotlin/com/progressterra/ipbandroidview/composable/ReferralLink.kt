package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ReferralLink(
    modifier: Modifier = Modifier,
    promoCode: () -> String,
    onCopy: () -> Unit,
    onShare: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .padding(AppTheme.dimensions.medium),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiny)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.smany)
            ) {
                Text(
                    text = promoCode(),
                    color = AppTheme.colors.black,
                    style = AppTheme.typography.headLine
                )
                IconButton(onClick = onCopy) { CopyIcon() }
            }
            Text(
                text = stringResource(R.string.your_promo_code),
                color = AppTheme.colors.gray2,
                style = AppTheme.typography.secondaryText
            )
        }
        IconButton(onClick = onShare) { ShareIcon() }
    }
}