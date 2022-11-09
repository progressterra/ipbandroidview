package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.progressterra.ipbandroidview.dto.component.Images
import com.progressterra.ipbandroidview.theme.AppTheme
import com.skydoves.landscapist.ImageOptions

interface GalleryState : Images

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Gallery(modifier: Modifier = Modifier, state: GalleryState) {
    val pagerState = rememberPagerState()
    HorizontalPager(
        modifier = modifier,
        count = state.images.size,
        state = pagerState
    ) {
        Box {
            SimpleImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(AppTheme.shapes.medium)
                    .background(AppTheme.colors.surfaces),
                url = state.images[it],
                options = ImageOptions(contentScale = ContentScale.FillBounds),
                backgroundColor = AppTheme.colors.surfaces
            )
            HorizontalPagerIndicator(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(8.dp)
                    .clip(CircleShape)
                    .background(AppTheme.colors.background)
                    .padding(vertical = 4.dp, horizontal = 6.dp),
                pagerState = pagerState,
                activeColor = AppTheme.colors.primary,
                inactiveColor = AppTheme.colors.surfaces
            )
        }
    }
}

private class GalleryStatePreview(override val images: List<String> = emptyList()) :
    GalleryState

@Preview
@Composable
private fun GalleryPreview() {
    AppTheme {
        LazyColumn {
            item {
                Gallery(
                    modifier = Modifier.size(350.dp),
                    state = GalleryStatePreview(images = listOf("", "", ""))
                )
            }
        }
    }
}