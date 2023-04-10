package com.progressterra.ipbandroidview.shared.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun ThemedLayout(
    modifier: Modifier = Modifier,
    backgroundColor: Brush = IpbTheme.colors.background.asBrush(),
    topBar: @Composable () -> Unit = {},
    topOverlap: Boolean = false,
    bottomBar: @Composable () -> Unit = {},
    bottomOverlap: Boolean = false,
    content: @Composable (topPadding: Dp, bottomPadding: Dp) -> Unit
) {
    Box(
        modifier = modifier
            .background(backgroundColor)
    ) {
        val density = LocalDensity.current
        var topBarHeight by remember { mutableStateOf(0.dp) }
        var bottomBarHeight by remember { mutableStateOf(0.dp) }
        Box(
            modifier = Modifier
                .zIndex(1f)
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .onGloballyPositioned { topBarHeight = with(density) { it.size.height.toDp() } },
            contentAlignment = Alignment.Center
        ) {
            topBar()
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = if (topOverlap) 0.dp else topBarHeight,
                    bottom = if (bottomOverlap) 0.dp else bottomBarHeight
                ), contentAlignment = Alignment.Center
        ) {
            content(
                if (topOverlap) topBarHeight else 0.dp,
                if (bottomOverlap) bottomBarHeight else 0.dp
            )
        }
        Box(
            modifier = Modifier
                .zIndex(1f)
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .onGloballyPositioned { bottomBarHeight = with(density) { it.size.height.toDp() } },
            contentAlignment = Alignment.Center
        ) {
            bottomBar()
        }
    }
}