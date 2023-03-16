package com.progressterra.ipbandroidview.shared.ui

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource

@Composable
fun BrushedIcon(
    modifier: Modifier = Modifier,
    resId: Int,
    tint: Brush
) {
    Icon(
        modifier = modifier
            .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
            .drawWithCache {
                onDrawWithContent {
                    drawContent()
                    drawRect(tint, blendMode = BlendMode.SrcAtop)
                }
            }, painter = painterResource(id = resId), contentDescription = null
    )
}