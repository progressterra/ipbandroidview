package com.progressterra.ipbandroidview.composable.component

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
import com.progressterra.ipbandroidview.composable.FavoriteButton
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.composable.TrashIcon
import com.progressterra.ipbandroidview.shared.ui.niceClickable
import com.progressterra.ipbandroidview.model.Favorite
import com.progressterra.ipbandroidview.model.GoodsSize
import com.progressterra.ipbandroidview.model.Id
import com.progressterra.ipbandroidview.model.OrderGoods
import com.progressterra.ipbandroidview.model.SimplePrice
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

private val picWidth = 80.dp

private val picHeight = 96.dp

data class CartCardState(
    override val id: String,
    val color: String,
    override val favorite: Boolean,
    val image: String,
    val inCartCounter: Int,
    val name: String,
    val price: SimplePrice,
    val size: GoodsSize
) : Id, Favorite<CartCardState> {

    override fun reverseFavorite(): CartCardState = this.copy(favorite = !favorite)

    fun toOrderGoods(): OrderGoods = OrderGoods(
        id = this.id,
        inCartCounter = this.inCartCounter,
        image = this.image,
        name = name,
        totalPrice = price * inCartCounter
    )
}

interface CartCardInteractor {

    fun favorite(cartCard: CartCardState)
    fun delete(cartCard: CartCardState)
    fun onDetails(cartCard: CartCardState)

    class Empty : CartCardInteractor {
        override fun favorite(cartCard: CartCardState) = Unit
        override fun delete(cartCard: CartCardState) = Unit
        override fun onDetails(cartCard: CartCardState) = Unit
    }
}

/**
 * @param modifier - modifier for root layout
 * @param state - state of component
 * @param interactor - interactor of component
 */
@Composable
fun CartCard(
    modifier: Modifier = Modifier,
    state: CartCardState,
    interactor: CartCardInteractor
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(IpbTheme.shapes.medium)
            .background(IpbTheme.colors.surfaces)
            .niceClickable { interactor.onDetails(state) }
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SimpleImage(
            modifier = Modifier
                .size(width = picWidth, height = picHeight)
                .clip(IpbTheme.shapes.small),
            url = state.image,
            backgroundColor = IpbTheme.colors.surfaces
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = state.name, color = IpbTheme.colors.black, style = IpbTheme.typography.text
            )
            Text(
                text = "${state.color}, ${state.size.primary}, ${state.inCartCounter} шт",
                color = IpbTheme.colors.gray1,
                style = IpbTheme.typography.text
            )
            Text(
                text = state.price.toString(),
                color = IpbTheme.colors.black,
                style = IpbTheme.typography.title
            )
        }
        Column {
            FavoriteButton(favorite = state.favorite, onClick = { interactor.favorite(state) })
            IconButton(onClick = { interactor.delete(state) }) {
                TrashIcon(enabled = false)
            }
        }
    }
}

@Preview
@Composable
private fun CartCardPreview() {
    IpbTheme {
        CartCard(
            state = CartCardState(
                id = "",
                color = "GREEN",
                favorite = true,
                image = "",
                inCartCounter = 30,
                name = "YOOOY SO COOL ITEM",
                price = SimplePrice(3000),
                size = GoodsSize(true, "", null)
            ), interactor = CartCardInteractor.Empty()
        )
    }
}