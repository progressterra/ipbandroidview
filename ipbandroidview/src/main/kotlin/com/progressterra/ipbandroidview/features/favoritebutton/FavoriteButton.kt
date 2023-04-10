package com.progressterra.ipbandroidview.features.favoritebutton

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Column
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    favorite: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier, onClick = onClick
    ) {
        BrushedIcon(
            resId = R.drawable.ic_favorite_off,
            tint = IpbTheme.colors.iconTertiary1.asBrush()
        )
        Column {
            AnimatedVisibility(
                visible = favorite,
                enter = scaleIn(),
                exit = scaleOut()
            ) {
                BrushedIcon(
                    resId = R.drawable.ic_favorite_on,
                    tint = IpbTheme.colors.iconPressed.asBrush()
                )
            }
        }
    }
}