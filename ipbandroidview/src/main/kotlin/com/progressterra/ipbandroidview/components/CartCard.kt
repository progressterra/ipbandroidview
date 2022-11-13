package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.components.utils.niceClickable
import com.progressterra.ipbandroidview.model.component.Color
import com.progressterra.ipbandroidview.model.component.Favorite
import com.progressterra.ipbandroidview.model.component.Image
import com.progressterra.ipbandroidview.model.component.InCartCounter
import com.progressterra.ipbandroidview.model.component.Name
import com.progressterra.ipbandroidview.model.component.Price
import com.progressterra.ipbandroidview.model.component.Size
import com.progressterra.ipbandroidview.theme.AppTheme

interface CartCardState : InCartCounter, Name, Image, Favorite, Color, Size, Price

@Composable
fun CartCard(
    modifier: Modifier = Modifier,
    state: () -> CartCardState,
    onFavorite: () -> Unit,
    onDelete: () -> Unit,
    onDetails: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .niceClickable(onDetails)
            .padding(AppTheme.dimensions.medium),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium)
    ) {
        SimpleImage(
            modifier = Modifier.size(width = 80.dp, height = 96.dp),
            url = state()::image,
            backgroundColor = AppTheme.colors.surfaces
        )
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
                text = state().name,
                color = AppTheme.colors.black,
                style = AppTheme.typography.text
            )
            Text(
                text = "${state().color}, ${state().size}, ${state().inCartCounter} шт",
                color = AppTheme.colors.black,
                style = AppTheme.typography.text
            )
            Text(
                text = state().price,
                color = AppTheme.colors.black,
                style = AppTheme.typography.text
            )
        }
        Column {
            FavoriteButton(favorite = state()::favorite, onClick = onFavorite)
            IconButton(onClick = onDelete) {
                TrashIcon(enabled = { false })
            }
        }
    }
}