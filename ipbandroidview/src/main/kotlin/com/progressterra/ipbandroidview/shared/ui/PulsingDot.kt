package com.progressterra.ipbandroidview.shared.ui

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun PulsingDot(modifier: Modifier = Modifier) {

    val infiniteTransition = rememberInfiniteTransition()

    @Composable
    fun Dot(
        modifier: Modifier,
        scale: Float
    ) = Spacer(
        modifier
            .size(48.dp)
            .scale(scale)
            .background(brush = IpbTheme.colors.primary.asBrush(), shape = CircleShape, alpha = 0.5f)
    )

    @Composable
    fun animateScale() = infiniteTransition.animateFloat(
        label = "Pulsing dot animation",
        initialValue = 1f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(animation = tween(), repeatMode = RepeatMode.Reverse)
    )

    val scale by animateScale()
    Dot(modifier, scale)
}