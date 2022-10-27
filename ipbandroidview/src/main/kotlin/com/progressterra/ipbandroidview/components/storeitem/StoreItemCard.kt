package com.progressterra.ipbandroidview.components.storeitem

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import com.progressterra.ipbandroidview.components.FavoriteCheckedIcon
import com.progressterra.ipbandroidview.components.FavoriteUncheckedIcon
import com.progressterra.ipbandroidview.components.SimpleImage
import com.progressterra.ipbandroidview.components.utils.niceClickable
import com.progressterra.ipbandroidview.theme.AppTheme
import com.skydoves.landscapist.ImageOptions

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun StoreItemCard(
    modifier: Modifier,
    state: StoreItemState,
    onClick: () -> Unit,
    onFavorite: () -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(AppTheme.roundings.mediumRounding))
            .background(AppTheme.colors.surfaces)
            .niceClickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            FavoriteUncheckedIcon()
            Column {
                AnimatedVisibility(
                    visible = state.favorite,
                    enter = scaleIn(),
                    exit = scaleOut()
                ) {
                    FavoriteCheckedIcon()
                }
            }
        }
        SimpleImage(
            url = state.imageUrl, options = ImageOptions(
                contentScale = ContentScale.FillBounds
            ), backgroundColor = AppTheme.colors.surfaces
        )
    }
}