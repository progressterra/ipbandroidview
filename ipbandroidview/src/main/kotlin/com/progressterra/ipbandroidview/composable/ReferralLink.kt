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
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun ReferralLink(
    modifier: Modifier = Modifier,
    promoCode: String,
    onCopy: () -> Unit,
    onShare: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(IpbTheme.shapes.medium)
            .background(IpbTheme.colors.surfaces)
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = promoCode,
                    color = IpbTheme.colors.black,
                    style = IpbTheme.typography.headLine
                )
                IconButton(onClick = onCopy) { CopyIcon() }
            }
            Text(
                text = stringResource(R.string.your_promo_code),
                color = IpbTheme.colors.gray2,
                style = IpbTheme.typography.secondaryText
            )
        }
        IconButton(onClick = onShare) { ShareIcon() }
    }
}