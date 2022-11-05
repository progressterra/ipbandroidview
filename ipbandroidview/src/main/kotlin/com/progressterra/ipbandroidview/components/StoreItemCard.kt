package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.progressterra.ipbandroidview.components.utils.niceClickable
import com.progressterra.ipbandroidview.dto.Favorite
import com.progressterra.ipbandroidview.dto.Image
import com.progressterra.ipbandroidview.dto.Name
import com.progressterra.ipbandroidview.dto.Price
import com.progressterra.ipbandroidview.theme.AppTheme
import com.skydoves.landscapist.ImageOptions

interface StoreItemCardState : Image, Favorite, Name, Price

@Composable
fun StoreItemCard(
    modifier: Modifier = Modifier,
    state: StoreItemCardState,
    onClick: () -> Unit,
    onFavorite: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .niceClickable(onClick = onClick)
    ) {
        val (favoriteButton, image, price, name) = createRefs()
        FavoriteButton(
            modifier = Modifier
                .zIndex(1f)
                .constrainAs(favoriteButton) {
                    end.linkTo(image.end)
                    top.linkTo(image.top)
                }, favorite = state.favorite, onClick = onFavorite
        )
        SimpleImage(
            Modifier
                .clip(
                    AppTheme.shapes.small.copy(topStart = CornerSize(0), topEnd = CornerSize(0))
                )
                .constrainAs(image) {
                    height = Dimension.value(236.dp)
                    width = Dimension.matchParent
                    top.linkTo(parent.top)
                }, url = state.image, options = ImageOptions(
                contentScale = ContentScale.FillBounds
            ), backgroundColor = AppTheme.colors.surfaces
        )
        val margin = 8.dp
        Text(
            modifier = Modifier.constrainAs(price) {
                width = Dimension.fillToConstraints
                top.linkTo(image.bottom, 4.dp)
                start.linkTo(image.start, margin)
                end.linkTo(image.end, margin)
            },
            text = state.price,
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
                top.linkTo(price.bottom, 2.dp)
                start.linkTo(image.start, margin)
                end.linkTo(image.end, margin)
                bottom.linkTo(parent.bottom, margin)
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
    }
}

private class StoreItemCardStatePreview(
    override val favorite: Boolean,
    override val image: String,
    override val name: String,
    override val price: String
) : StoreItemCardState

@Preview
@Composable
private fun StoreItemCardPreview() {
    AppTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            StoreItemCard(state = StoreItemCardStatePreview(
                image = "",
                price = "3 000 ₽",
                name = "Some cool item with pretty long name that contains many symbols",
                favorite = false
            ), onClick = { }) {}
            StoreItemCard(state = StoreItemCardStatePreview(
                price = "3 000 ₽",
                name = "Some cool item",
                favorite = true,
                image = ""
            ), onClick = { }) {}
        }

    }
}