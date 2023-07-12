package com.progressterra.ipbandroidview.features.favoritebutton

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier, state: FavoriteButtonState, useComponent: UseFavoriteButton
) {
    IconButton(
        modifier = modifier.size(24.dp),
        onClick = { useComponent.handle(FavoriteButtonEvent(state.id)) },
        enabled = state.enabled
    ) {
        BrushedIcon(
            resId = R.drawable.ic_favorite_off, tint = IpbTheme.colors.iconTertiary.asBrush()
        )
        Column {
            AnimatedVisibility(
                visible = state.favorite, enter = scaleIn(), exit = scaleOut()
            ) {
                BrushedIcon(
                    resId = R.drawable.ic_favorite_on, tint = IpbTheme.colors.iconPressed.asBrush()
                )
            }
        }
    }
}