package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.ext.fromHexToColor
import com.progressterra.ipbandroidview.model.store.GoodsColor
import com.progressterra.ipbandroidview.theme.AppTheme

private val picWidth = 56.dp

private val picHeight = 64.dp

private val borderWidth = 1.dp

@Composable
fun ColorsLine(
    modifier: Modifier = Modifier,
    colors: List<GoodsColor>,
    color: String,
    chooseColor: (GoodsColor) -> Unit
) {

    @Composable
    fun Item(itemColor: GoodsColor) {
        Box(
            modifier = Modifier
                .size(width = picWidth, height = picHeight)
                .clip(AppTheme.shapes.small)
                .border(
                    width = borderWidth,
                    color = if (itemColor.name == color) AppTheme.colors.primary else Transparent,
                    AppTheme.shapes.small
                )
                .niceClickable(onClick = { chooseColor(itemColor) })
                .padding(AppTheme.dimensions.tiniest),
            contentAlignment = Alignment.Center
        ) {
            if (itemColor.hex.isNotBlank())
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(AppTheme.shapes.tiny)
                        .background(itemColor.hex.fromHexToColor())
                )
            else
                SimpleImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(AppTheme.shapes.tiny),
                    url = itemColor.image,
                    backgroundColor = AppTheme.colors.surfaces
                )
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .padding(AppTheme.dimensions.medium),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiny)
    ) {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)) {
            items(colors) {
                Item(it)
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiny)) {
            Text(
                text = stringResource(id = R.string.color),
                color = AppTheme.colors.gray2,
                style = AppTheme.typography.tertiaryText
            )
            Text(
                text = color,
                color = AppTheme.colors.gray1,
                style = AppTheme.typography.tertiaryText
            )
        }
    }
}

@Preview
@Composable
private fun ColorsLinePreview() {
    AppTheme {
        val current = GoodsColor(
            name = "M", image = ""
        )
        ColorsLine(
            color = current.name,
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
            ), chooseColor = {}
        )
    }
}
