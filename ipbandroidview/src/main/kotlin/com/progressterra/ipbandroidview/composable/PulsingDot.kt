package com.progressterra.ipbandroidview.composable

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.Dp
import com.progressterra.ipbandroidview.theme.AppTheme


@Composable
fun PulsingDot(modifier: Modifier = Modifier, size: Dp) {

    val infiniteTransition = rememberInfiniteTransition()

    @Composable
    fun Dot(
        modifier: Modifier,
        scale: Float
    ) = Spacer(
        modifier
            .size(size)
            .scale(scale)
            .background(color = AppTheme.colors.primary.copy(alpha = 0.5f), shape = CircleShape)
    )

    @Composable
    fun animateScale() = infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.75f,
        animationSpec = infiniteRepeatable(animation = tween(), repeatMode = RepeatMode.Reverse)
    )

    val scale by animateScale()
    Dot(modifier, scale)
}