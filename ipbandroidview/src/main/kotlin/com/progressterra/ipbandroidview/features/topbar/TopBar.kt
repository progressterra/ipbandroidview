package com.progressterra.ipbandroidview.features.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    showBackButton: Boolean = false,
    useComponent: UseTopBar
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .background(IpbTheme.colors.background.asBrush())
    ) {
        if (showBackButton) {
            IconButton(modifier = Modifier
                .size(30.dp)
                .align(Alignment.CenterStart),
                onClick = { useComponent.handle(TopBarEvent) }) {
                BrushedIcon(
                    modifier = Modifier.size(30.dp),
                    resId = R.drawable.ic_back, tint = IpbTheme.colors.iconPrimary.asBrush()
                )
            }
        }
        BrushedText(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 40.dp),
            text = title,
            maxLines = 1,
            style = IpbTheme.typography.title,
            tint = IpbTheme.colors.textPrimary.asBrush(),
        )
    }
}

@Preview
@Composable
private fun TopBarPreview() {
    IpbTheme {
        TopBar(
            title = "Some very long title title title title title title title",
            showBackButton = true,
            useComponent = UseTopBar.Empty()
        )
    }
}
