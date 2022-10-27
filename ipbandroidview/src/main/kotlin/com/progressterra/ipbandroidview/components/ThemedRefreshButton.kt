package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ThemedRefreshButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Icon(
        modifier = modifier
            .size(64.dp)
            .clip(RoundedCornerShape(AppTheme.roundings.buttonRounding))
            .background(AppTheme.colors.primary)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = onClick
            )
            .padding(16.dp),
        painter = painterResource(id = R.drawable.ic_refresh),
        contentDescription = stringResource(
            id = R.string.refresh
        ),
        tint = AppTheme.colors.surfaces
    )
}

@Preview
@Composable
private fun ThemedRefreshButtonPreview() {
    AppTheme {
        ThemedRefreshButton(onClick = {})
    }
}