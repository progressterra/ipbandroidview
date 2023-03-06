package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.progressterra.ipbandroidview.composable.FavoriteButton
import com.progressterra.ipbandroidview.composable.SimpleImage
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.model.Favorite
import com.progressterra.ipbandroidview.model.Id
import com.progressterra.ipbandroidview.model.SimplePrice
import com.progressterra.ipbandroidview.theme.AppTheme

private const val aspectRatio = 0.5f

data class StoreCardComponentState(
    override val id: String,
    val name: String,
    val image: String,
    val price: SimplePrice,
    override val favorite: Boolean
) : Id, Favorite<StoreCardComponentState> {

    override fun reverseFavorite(): StoreCardComponentState = this.copy(favorite = !favorite)
}

interface StoreCardInteractor {

    fun onClick(storeCard: StoreCardComponentState)
    fun favorite(storeCard: StoreCardComponentState)

    class Empty : StoreCardInteractor {
        override fun onClick(storeCard: StoreCardComponentState) = Unit
        override fun favorite(storeCard: StoreCardComponentState) = Unit
    }
}

/**
 * @param modifier - modifier for the whole card
 * @param state - state of the card
 * @param interactor - interactor for the card
 */
@Composable
fun StoreCardComponent(
    modifier: Modifier = Modifier, state: StoreCardComponentState, interactor: StoreCardInteractor
) {
    ConstraintLayout(
        modifier = modifier
            .aspectRatio(aspectRatio, matchHeightConstraintsFirst = true)
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .niceClickable { interactor.onClick(state) }
    ) {
        val (favoriteButton, image, price, name) = createRefs()
        SimpleImage(
            Modifier
                .clip(AppTheme.shapes.small)
                .constrainAs(image) {
                    height = Dimension.fillToConstraints
                    width = Dimension.matchParent
                    top.linkTo(parent.top)
                    bottom.linkTo(price.top)
                }, url = state.image, backgroundColor = AppTheme.colors.surfaces
        )
        val large = 8.dp
        val medium = 4.dp
        val small = 2.dp
        Text(
            modifier = Modifier.constrainAs(price) {
                width = Dimension.fillToConstraints
                top.linkTo(image.bottom, medium)
                start.linkTo(image.start, large)
                end.linkTo(image.end, large)
            },
            text = state.price.toString(),
            color = AppTheme.colors.black,
            maxLines = 1,
            style = AppTheme.typography.title,
            textAlign = TextAlign.Center
        )
        val lines = 2
        var nameText by remember(state.name) { mutableStateOf(state.name) }
        Text(
            modifier = Modifier.constrainAs(name) {
                width = Dimension.fillToConstraints
                top.linkTo(price.bottom, small)
                start.linkTo(image.start, large)
                end.linkTo(image.end, large)
                bottom.linkTo(parent.bottom, large)
            },
            text = nameText,
            maxLines = lines,
            color = AppTheme.colors.gray1,
            style = AppTheme.typography.secondaryText,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { textLayoutResult ->
                if ((textLayoutResult.lineCount) < lines) {
                    nameText = state.name + "\n ".repeat(lines - textLayoutResult.lineCount)
                }
            },
        )
        FavoriteButton(modifier = Modifier.constrainAs(favoriteButton) {
            end.linkTo(image.end)
            top.linkTo(image.top)
        }, favorite = state.favorite, onClick = { interactor.favorite(state) })
    }
}

@Preview
@Composable
private fun StoreItemCardPreview() {
    AppTheme {
        Row(
            modifier = Modifier.height(200.dp),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.large)
        ) {
            StoreCardComponent(
                modifier = Modifier.weight(1f), state = StoreCardComponentState(
                    id = "",
                    image = "",
                    price = SimplePrice(0),
                    name = "Some cool item with pretty long name that contains many symbols",
                    favorite = false
                ), interactor = StoreCardInteractor.Empty()
            )
            StoreCardComponent(
                modifier = Modifier.weight(1f), state = StoreCardComponentState(
                    id = "",
                    price = SimplePrice(0),
                    name = "Some cool item",
                    favorite = true,
                    image = ""
                ), interactor = StoreCardInteractor.Empty()
            )
        }
    }
}