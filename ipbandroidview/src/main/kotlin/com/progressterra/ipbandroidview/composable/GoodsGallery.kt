package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.model.store.StoreGoods
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun GoodsGallery(
    modifier: Modifier = Modifier,
    goods: List<StoreGoods>,
    title: String,
    onGoodsDetails: (StoreGoods) -> Unit,
    onFavorite: (StoreGoods) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
    ) {
        Text(
            text = title,
            color = AppTheme.colors.black,
            style = AppTheme.typography.title
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(AppTheme.dimensions.small),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
        ) {
            items(goods) { item ->
                StoreCard(
                    state = item,
                    onClick = { onGoodsDetails(item) },
                    onFavorite = { onFavorite(item) }
                )
            }
        }
    }
}

@Preview
@Composable
private fun GoodsGalleryPreview() {
    AppTheme {
        GoodsGallery(
            goods = listOf(),
            onGoodsDetails = {},
            onFavorite = {},
            title = "Some cool title"
        )
    }
}