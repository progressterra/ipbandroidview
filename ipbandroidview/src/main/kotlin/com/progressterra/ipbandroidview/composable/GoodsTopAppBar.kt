package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.progressterra.ipbandroidview.model.GoodsDetails
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun GoodsTopAppBar(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onFavorite: () -> Unit,
    state: GoodsDetails,
) {
    BasicTopAppBar(modifier = modifier, backgroundColor = IpbTheme.colors.surfaces, leftActions = {
        IconButton(onClick = onBack) {
            BackIcon()
        }
    }, title = {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = state.name,
                color = IpbTheme.colors.black,
                style = IpbTheme.typography.text,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
            Text(
                text = state.price.toString(),
                color = IpbTheme.colors.black,
                style = IpbTheme.typography.tertiaryText,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        }
    }, rightActions = {
        FavoriteButton(favorite = state.favorite, onClick = onFavorite)
    })
}