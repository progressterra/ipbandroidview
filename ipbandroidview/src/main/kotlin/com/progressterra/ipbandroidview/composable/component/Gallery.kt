package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.progressterra.ipbandroidview.composable.element.SimpleImage
import com.progressterra.ipbandroidview.model.GoodsDetails
import com.progressterra.ipbandroidview.theme.AppTheme


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Gallery(modifier: Modifier = Modifier, state: () -> GoodsDetails) {
    val pagerState = rememberPagerState()
    Box {
        HorizontalPager(
            modifier = modifier,
            count = state().images.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = AppTheme.dimensions.small),
            itemSpacing = AppTheme.dimensions.small
        ) {
            SimpleImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(AppTheme.shapes.medium)
                    .background(AppTheme.colors.surfaces),
                url = { state().images[it] },
                backgroundColor = AppTheme.colors.surfaces
            )
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(AppTheme.dimensions.small)
                .clip(CircleShape)
                .background(AppTheme.colors.background)
                .padding(
                    vertical = AppTheme.dimensions.tiny,
                    horizontal = AppTheme.dimensions.smany
                ),
            pagerState = pagerState,
            activeColor = AppTheme.colors.primary,
            inactiveColor = AppTheme.colors.surfaces
        )
    }
}

@Preview
@Composable
private fun GalleryPreview() {
    AppTheme {
        LazyColumn {
            item {
                Gallery(
                    modifier = Modifier.size(350.dp),
                    state = {
                        GoodsDetails()
                    }
                )
            }
        }
    }
}