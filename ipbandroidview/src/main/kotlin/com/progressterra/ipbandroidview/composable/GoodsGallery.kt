package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.composable.component.StoreCardComponent
import com.progressterra.ipbandroidview.composable.component.StoreCardComponentState
import com.progressterra.ipbandroidview.composable.component.StoreCardInteractor
import com.progressterra.ipbandroidview.entities.Category
import com.progressterra.ipbandroidview.entities.SimpleCategory
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

private val height = 260.dp

@Composable
fun GoodsGallery(
    modifier: Modifier = Modifier,
    goods: List<StoreCardComponentState>,
    category: Category,
    storeCardInteractor: StoreCardInteractor,
    onFullCategory: (Category) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = category.name, color = IpbTheme.colors.black, style = IpbTheme.typography.title
        )
        LazyRow(
            modifier = Modifier.height(height),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(goods) { item ->
                StoreCardComponent(
                    state = item, interactor = storeCardInteractor
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
    IpbTheme {
        GoodsGallery(
            goods = listOf(
                StoreCardComponentState(
                    id = "1",
                    name = "name",
                    image = "image",
                    price = SimplePrice(1000),
                    favorite = false
                ), StoreCardComponentState(
                    id = "2",
                    name = "name",
                    image = "image",
                    price = SimplePrice(1000),
                    favorite = false
                )
            ),
            storeCardInteractor = StoreCardInteractor.Empty(),
            onFullCategory = {},
            category = SimpleCategory("name", "image")
        )
    }
}