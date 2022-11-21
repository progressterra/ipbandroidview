package com.progressterra.ipbandroidview.composable.element

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Column
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    favorite: () -> Boolean,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier, onClick = onClick
    ) {
        FavoriteUncheckedIcon()
        Column {
            AnimatedVisibility(
                visible = favorite(),
                enter = scaleIn(),
                exit = scaleOut()
            ) {
                FavoriteCheckedIcon()
            }
        }
    }
}