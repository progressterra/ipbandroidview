package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.element.BasicTopAppBar
import com.progressterra.ipbandroidview.composable.element.FavoriteButton
import com.progressterra.ipbandroidview.model.GoodsDetails
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
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                tint = AppTheme.colors.gray1
            )
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
                text = state().price.formattedPrice,
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