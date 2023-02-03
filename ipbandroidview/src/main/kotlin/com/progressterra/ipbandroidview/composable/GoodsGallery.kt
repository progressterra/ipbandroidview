package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.model.store.Category
import com.progressterra.ipbandroidview.model.store.SimpleCategory
import com.progressterra.ipbandroidview.model.store.SimplePrice
import com.progressterra.ipbandroidview.model.store.StoreGoods
import com.progressterra.ipbandroidview.theme.AppTheme

private val height = 260.dp

@Composable
fun GoodsGallery(
    modifier: Modifier = Modifier,
    goods: List<StoreGoods>,
    category: Category,
    onGoodsDetails: (StoreGoods) -> Unit,
    onFavorite: (StoreGoods) -> Unit,
    onFullCategory: (Category) -> Unit
) {
    Column(
        modifier = modifier.padding(AppTheme.dimensions.small),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
    ) {
        Text(
            text = category.name,
            color = AppTheme.colors.black,
            style = AppTheme.typography.title
        )
        LazyRow(
            modifier = Modifier.height(height),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(goods) { item ->
                StoreCard(
                    state = item,
                    onClick = { onGoodsDetails(item) },
                    onFavorite = { onFavorite(item) }
                )
            }
            item {
                IconButton(onClick = { onFullCategory(category) }) {
                    ForwardIcon()
                }
            }
        }
    }
}

@Preview
@Composable
private fun GoodsGalleryPreview() {
    AppTheme {
        GoodsGallery(
            goods = listOf(
                StoreGoods(
                    id = "1",
                    name = "name",
                    image = "image",
                    price = SimplePrice(1000),
                    favorite = false
                ),
                StoreGoods(
                    id = "2",
                    name = "name",
                    image = "image",
                    price = SimplePrice(1000),
                    favorite = false
                )
            ),
            onGoodsDetails = {},
            onFavorite = {},
            onFullCategory = {},
            category = SimpleCategory("name", "image")
        )
    }
}