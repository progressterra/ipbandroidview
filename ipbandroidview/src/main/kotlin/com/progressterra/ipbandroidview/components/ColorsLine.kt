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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.utils.niceClickable
import com.progressterra.ipbandroidview.model.component.Color
import com.progressterra.ipbandroidview.model.component.Colors
import com.progressterra.ipbandroidview.model.GoodsColor
import com.progressterra.ipbandroidview.theme.AppTheme
import com.skydoves.landscapist.ImageOptions

interface ColorsLineState : Color, Colors

@Composable
fun ColorsLine(
    modifier: Modifier = Modifier, state: ColorsLineState, onColor: (GoodsColor) -> Unit
) {

    @Composable
    fun Item(color: GoodsColor) {
        Box(
            modifier = Modifier
                .size(width = 56.dp, height = 64.dp)
                .clip(AppTheme.shapes.small)
                .border(
                    width = 1.dp,
                    color = if (color == state.color) AppTheme.colors.primary else Transparent,
                    AppTheme.shapes.small
                )
                .niceClickable({ onColor(color) })
                .padding(2.dp),
            contentAlignment = Alignment.Center
        ) {
            SimpleImage(
                modifier = Modifier.clip(AppTheme.shapes.tiny),
                url = color.image,
                backgroundColor = AppTheme.colors.surfaces
            )
        }
    }
    Column(
        modifier = modifier
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(state.colors) {
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
                text = state.color.name,
                color = AppTheme.colors.gray1,
                style = AppTheme.typography.tertiaryText
            )
        }
    }
}

private class ColorsLineStatePreview(
    override val color: GoodsColor,
    override val colors: List<GoodsColor>
) : ColorsLineState

@Preview
@Composable
private fun ColorsLinePreview() {
    AppTheme {
        val current = GoodsColor(
            name = "M", image = ""
        )
        ColorsLine(
            state = ColorsLineStatePreview(
                color = current,
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
                )
            ), onColor = {}
        )
    }
}
