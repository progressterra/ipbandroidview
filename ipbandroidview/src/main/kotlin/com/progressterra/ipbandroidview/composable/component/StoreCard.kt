package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.progressterra.ipbandroidview.composable.element.FavoriteButton
import com.progressterra.ipbandroidview.composable.element.SimpleImage
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.model.store.SimplePrice
import com.progressterra.ipbandroidview.model.store.StoreGoods
import com.progressterra.ipbandroidview.theme.AppTheme

private val picHeight = 236.dp

@Composable
fun StoreCard(
    modifier: Modifier = Modifier,
    state: () -> StoreGoods,
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
                }, favorite = state()::favorite, onClick = onFavorite
        )
        SimpleImage(
            Modifier
                .clip(
                    AppTheme.shapes.small
                )
                .constrainAs(image) {
                    height = Dimension.value(picHeight)
                    width = Dimension.matchParent
                    top.linkTo(parent.top)
                }, url = state()::image, backgroundColor = AppTheme.colors.surfaces
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
            text = state().price.toString(),
            color = AppTheme.colors.black,
            maxLines = 1,
            style = AppTheme.typography.title,
            textAlign = TextAlign.Center
        )
        val lines = 2
        var nameText by remember(state().name) { mutableStateOf(state().name) }
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
                    nameText = state().name + "\n ".repeat(lines - textLayoutResult.lineCount)
                }
            },
        )
    }
}

@Preview
@Composable
private fun StoreItemCardPreview() {
    AppTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.large)) {
            StoreCard(state = {
                StoreGoods(
                    id = "",
                    image = "",
                    price = SimplePrice(0),
                    name = "Some cool item with pretty long name that contains many symbols",
                    favorite = false
                )
            }, onClick = {}, onFavorite = {})
            StoreCard(state = {
                StoreGoods(
                    id = "",
                    price = SimplePrice(0),
                    name = "Some cool item",
                    favorite = true,
                    image = ""
                )
            }, onClick = {}, onFavorite = {})
        }
    }
}