package com.progressterra.ipbandroidview.composable

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.model.store.CartGoods
import com.progressterra.ipbandroidview.model.store.GoodsColor
import com.progressterra.ipbandroidview.model.store.GoodsSize
import com.progressterra.ipbandroidview.model.store.SimplePrice
import com.progressterra.ipbandroidview.theme.AppTheme

private val picWidth = 80.dp

private val picHeight = 96.dp

@Composable
fun CartCard(
    modifier: Modifier = Modifier,
    state: () -> CartGoods,
    onFavorite: (CartGoods) -> Unit,
    onDelete: (CartGoods) -> Unit,
    onDetails: (CartGoods) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .niceClickable(onClick = { onDetails(state()) })
            .padding(AppTheme.dimensions.small),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
    ) {
        SimpleImage(
            modifier = Modifier
                .size(width = picWidth, height = picHeight)
                .clip(AppTheme.shapes.small),
            url = state()::image,
            backgroundColor = AppTheme.colors.surfaces
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.smany)
        ) {
            Text(
                text = state().name,
                color = AppTheme.colors.black,
                style = AppTheme.typography.text
            )
            Text(
                text = "${state().color.name}, ${state().size.primary}, ${state().inCartCounter} шт",
                color = AppTheme.colors.gray1,
                style = AppTheme.typography.text
            )
            Text(
                text = state().price.toString(),
                color = AppTheme.colors.black,
                style = AppTheme.typography.title
            )
        }
        Column {
            FavoriteButton(favorite = state()::favorite, onClick = { onFavorite(state()) })
            IconButton(onClick = { onDelete(state()) }) {
                TrashIcon(enabled = { false })
            }
        }
    }
}

@Preview
@Composable
private fun CartCardPreview() {
    AppTheme {
        CartCard(state = {
            CartGoods(
                id = "",
                color = GoodsColor("", "GREEN"),
                favorite = true,
                image = "",
                inCartCounter = 30,
                name = "YOOOY SO COOL ITEM",
                price = SimplePrice(3000),
                size = GoodsSize(true, "", null)
            )
        }, onFavorite = {}, onDelete = {}, onDetails = {})
    }
}