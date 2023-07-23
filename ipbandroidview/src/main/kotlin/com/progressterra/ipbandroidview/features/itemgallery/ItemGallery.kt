package com.progressterra.ipbandroidview.features.itemgallery

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemGallery(
    modifier: Modifier = Modifier,
    state: ItemGalleryState,
    useComponent: UseItemGallery
) {
    val pagerState = rememberPagerState()
    Box(modifier = modifier) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            pageCount = state.images.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 20.dp),
            pageSpacing = 20.dp
        ) {
            SimpleImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
                    .niceClickable { useComponent.handle(ItemGalleryEvent(state.images[it])) },
                image = state.images[it],
                backgroundColor = IpbTheme.colors.onSurface.asColor()
            )
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(10.dp),
            pagerState = pagerState,
            pageCount = state.images.size,
            activeColor = IpbTheme.colors.onSurface.asColor(),
            inactiveColor = IpbTheme.colors.onSurface.asColor().copy(alpha = 0.4f),
            indicatorWidth = 10.dp
        )
    }
}

@Preview
@Composable
private fun ItemGalleryPreview() {
    IpbTheme {
        ItemGallery(
            state = ItemGalleryState(
                images = listOf(
                    "https://images.unsplash.com/photo-1616166334058-8b8f8f1b8f1a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=60",
                    "https://images.unsplash.com/photo-1616166334058-8b8f8f1b8f1a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=60",
                    "https://images.unsplash.com/photo-1616166334058-8b8f8f1b8f1a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=60",
                    "https://images.unsplash.com/photo-1616166334058-8b8f8f1b8f1a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=60",
                    "https://images.unsplash.com/photo-1616166334058-8b8f8f1b8f1a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=60"
                )
            ),
            useComponent = UseItemGallery.Empty()
        )
    }
}