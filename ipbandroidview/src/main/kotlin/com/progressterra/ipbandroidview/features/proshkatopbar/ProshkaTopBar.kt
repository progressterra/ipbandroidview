package com.progressterra.ipbandroidview.features.proshkatopbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Composable
fun ProshkaTopBar(
    modifier: Modifier = Modifier,
    title: String,
    showBackButton: Boolean = false,
    useComponent: UseTopBar
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .background(IpbTheme.colors.background.asBrush()),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (showBackButton) {
            IconButton(onClick = { useComponent.handle(ProshkaTopBarEvent.Back) }) {
                BrushedIcon(
                    resId = R.drawable.ic_back_pro, tint = IpbTheme.colors.iconPrimary.asBrush()
                )
            }
        }
        BrushedText(
            text = title,
            style = IpbTheme.typography.title,
            tint = IpbTheme.colors.textPrimary.asBrush(),
        )
    }
}