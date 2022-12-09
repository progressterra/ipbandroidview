package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.progressterra.ipbandroidview.composable.element.BackIcon
import com.progressterra.ipbandroidview.composable.element.BasicTopAppBar
import com.progressterra.ipbandroidview.composable.element.FavoriteButton
import com.progressterra.ipbandroidview.model.store.GoodsDetails
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun GoodsTopAppBar(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onFavorite: () -> Unit,
    state: () -> GoodsDetails,
) {
    BasicTopAppBar(modifier = modifier, backgroundColor = AppTheme.colors.surfaces, leftActions = {
        IconButton(onClick = onBack) {
            BackIcon()
        }
    }, title = {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = state().name,
                color = AppTheme.colors.black,
                style = AppTheme.typography.text,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
            Text(
                text = state().price.toString(),
                color = AppTheme.colors.black,
                style = AppTheme.typography.tertiaryText,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        }
    }, rightActions = {
        FavoriteButton(favorite = state()::favorite, onClick = onFavorite)
    })
}