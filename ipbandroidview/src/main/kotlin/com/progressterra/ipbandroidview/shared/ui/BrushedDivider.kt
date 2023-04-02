package com.progressterra.ipbandroidview.shared.ui

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BrushedDivider(
    modifier: Modifier = Modifier,
    tint: Color,
    thickness: Dp = 3.dp
) {
    Divider(
        modifier = modifier,
        color = tint,
        thickness = thickness
    )
}