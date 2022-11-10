package com.progressterra.ipbandroidview.components.topbar

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.FavoriteButton
import com.progressterra.ipbandroidview.model.component.Favorite
import com.progressterra.ipbandroidview.model.component.Name
import com.progressterra.ipbandroidview.model.component.Price
import com.progressterra.ipbandroidview.theme.AppTheme

interface GoodsTopAppBarState : Name, Favorite, Price

@Composable
fun GoodsTopAppBar(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onFavorite: () -> Unit,
    state: GoodsTopAppBarState,
) {
    BasicTopAppBar(modifier = modifier, backgroundColor = AppTheme.colors.surfaces, leftActions = {
        IconButton(onClick = onBack) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = stringResource(id = R.string.navigate_back),
                tint = AppTheme.colors.gray1
            )
        }
    }, title = {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = state.name,
                color = AppTheme.colors.black,
                style = AppTheme.typography.text,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
            Text(
                text = state.price,
                color = AppTheme.colors.black,
                style = AppTheme.typography.tertiaryText,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        }
    }, rightActions = {
        FavoriteButton(favorite = state.favorite, onClick = onFavorite)
    })
}