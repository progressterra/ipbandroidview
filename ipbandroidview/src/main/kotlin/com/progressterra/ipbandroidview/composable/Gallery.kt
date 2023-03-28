package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerScope
import com.progressterra.ipbandroidview.model.GoodsDetails
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.theme.IpbTheme


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Gallery(modifier: Modifier = Modifier, state: GoodsDetails) {
    val pagerState = androidx.compose.foundation.pager.rememberPagerState()
    Box(modifier = modifier.aspectRatio(1f)) {
        PaddingValues(horizontal = 8.dp)
        PagerDefaults.flingBehavior(
            state = state,
            endContentPadding = contentPadding.calculateEndPadding(LayoutDirection.Ltr),
        )
        fun PagerScope.(it: Int) {
            SimpleImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(IpbTheme.shapes.medium)
                    .background(IpbTheme.colors.surfaces),
                url = state.images[it],
                backgroundColor = IpbTheme.colors.surfaces
            )
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(8.dp)
                .clip(CircleShape)
                .background(IpbTheme.colors.background)
                .padding(
                    vertical = 4.dp, horizontal = 6.dp
                ),
            pagerState = pagerState,
            activeColor = IpbTheme.colors.primary,
            inactiveColor = IpbTheme.colors.surfaces
        )
    }
}

@Preview
@Composable
private fun GalleryPreview() {
    IpbTheme {
        LazyColumn {
            item {
                Gallery(modifier = Modifier.size(350.dp), state = GoodsDetails())
            }
        }
    }
}