package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.utils.niceClickable
import com.progressterra.ipbandroidview.dto.GoodsColor
import com.progressterra.ipbandroidview.theme.AppTheme
import com.skydoves.landscapist.ImageOptions

@Composable
fun ColorsLine(
    modifier: Modifier = Modifier,
    currentColor: GoodsColor,
    colors: List<GoodsColor>,
    onColor: (GoodsColor) -> Unit
) {

    @Composable
    fun Item(state: GoodsColor) {
        Box(
            modifier = Modifier
                .size(width = 56.dp, height = 64.dp)
                .clip(RoundedCornerShape(AppTheme.roundings.smallRounding))
                .border(
                    width = 1.dp,
                    color = if (state == currentColor) AppTheme.colors.primary else Color.Transparent,
                    RoundedCornerShape(AppTheme.roundings.smallRounding)
                )
                .niceClickable({ onColor(state) })
                .padding(2.dp),
            contentAlignment = Alignment.Center
        ) {
            SimpleImage(
                modifier = Modifier.clip(RoundedCornerShape(AppTheme.roundings.tinyRounding)),
                url = state.image,
                options = ImageOptions(contentScale = ContentScale.FillBounds),
                backgroundColor = AppTheme.colors.surfaces
            )
        }
    }
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(AppTheme.roundings.mediumRounding))
            .background(AppTheme.colors.surfaces)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(colors) {
                Item(it)
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = stringResource(id = R.string.color),
                color = AppTheme.colors.gray2,
                style = AppTheme.typography.tertiaryText
            )
            Text(
                text = currentColor.name,
                color = AppTheme.colors.gray1,
                style = AppTheme.typography.tertiaryText
            )
        }
    }
}

@Preview
@Composable
fun PicturesLinePreview() {
    AppTheme {
        val current = GoodsColor(
            name = "M", image = ""
        )
        ColorsLine(
            colors = listOf(
                current,
                GoodsColor(
                    name = "L", image = ""
                ),
                GoodsColor(
                    name = "XL", image = ""
                ),
                GoodsColor(
                    name = "XXL", image = ""
                ),
            ), onColor = {}, currentColor = current
        )
    }
}
