package com.progressterra.ipbandroidview.features.itemgallery

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.SimpleImage

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun Gallery(modifier: Modifier = Modifier, state: GalleryState) {
    val pagerState = rememberPagerState()
    Box(modifier = modifier) {
        HorizontalPager(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .size(width = 335.dp, height = 220.dp),
            pageCount = state.images.size,
            state = pagerState
        ) {
            SimpleImage(
                modifier = Modifier
                    .fillMaxSize(),
                url = state.images[it],
                backgroundColor = IpbTheme.colors.onSurface1.asColor()
            )
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(10.dp),
            pagerState = pagerState,
            pageCount = state.images.size,
            activeColor = IpbTheme.colors.onSurface1.asColor(),
            inactiveColor = IpbTheme.colors.onSurface1.asColor().copy(alpha = 0.4f),
            indicatorWidth = 10.dp
        )
    }
}