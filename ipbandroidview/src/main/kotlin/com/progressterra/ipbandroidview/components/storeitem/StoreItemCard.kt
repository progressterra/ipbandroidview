package com.progressterra.ipbandroidview.components.storeitem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.progressterra.ipbandroidview.components.FavoriteButton
import com.progressterra.ipbandroidview.components.SimpleImage
import com.progressterra.ipbandroidview.components.utils.niceClickable
import com.progressterra.ipbandroidview.theme.AppTheme
import com.skydoves.landscapist.ImageOptions

@Composable
fun StoreItemCard(
    modifier: Modifier = Modifier,
    state: StoreItemState,
    onClick: () -> Unit,
    onFavorite: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .clip(RoundedCornerShape(AppTheme.roundings.mediumRounding))
            .background(AppTheme.colors.surfaces)
            .niceClickable(onClick = onClick)
    ) {
        val (favoriteButton, image, price, name) = createRefs()
        FavoriteButton(modifier = Modifier.constrainAs(favoriteButton) {
            translationZ = 1.dp
            end.linkTo(parent.end)
            top.linkTo(parent.top)
        }, favorite = state.favorite, onClick = onFavorite)
        SimpleImage(
            Modifier
                .clip(
                    RoundedCornerShape(
                        bottomStart = AppTheme.roundings.smallRounding,
                        bottomEnd = AppTheme.roundings.smallRounding
                    )
                )
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, url = state.imageUrl, options = ImageOptions(
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
        Text(
            modifier = Modifier.constrainAs(name) {
                width = Dimension.fillToConstraints
                top.linkTo(price.bottom, 2.dp)
                start.linkTo(image.start, margin)
                end.linkTo(image.end, margin)
                bottom.linkTo(parent.bottom, margin)
            },
            text = state.name,
            maxLines = 2,
            color = AppTheme.colors.gray1,
            style = AppTheme.typography.secondaryText,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
private fun StoreItemCardPreview() {
    AppTheme {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            StoreItemCard(state = StoreItemState(
                imageUrl = "",
                "3 000 ₽",
                "Some cool item with pretty long name that contains many symbols",
                false
            ), onClick = { }) {}
            StoreItemCard(state = StoreItemState(
                imageUrl = "", "3 000 ₽", "Some cool item", true
            ), onClick = { }) {}
        }

    }
}