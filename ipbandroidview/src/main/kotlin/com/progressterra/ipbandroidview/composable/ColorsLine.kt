package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.progressterra.ipbandroidview.model.store.GoodsColor
import com.progressterra.ipbandroidview.model.store.GoodsDetails
import com.progressterra.ipbandroidview.theme.AppTheme

private val picWidth = 56.dp

private val picHeight = 64.dp

private val borderWidth = 1.dp

@Composable
fun ColorsLine(
    modifier: Modifier = Modifier,
    state: () -> GoodsDetails,
    chooseColor: (GoodsColor) -> Unit
) {

    @Composable
    fun Item(color: GoodsColor) {
        Box(
            modifier = Modifier
                .size(width = picWidth, height = picHeight)
                .clip(AppTheme.shapes.small)
                .border(
                    width = borderWidth,
                    color = if (color == state().color) AppTheme.colors.primary else Transparent,
                    AppTheme.shapes.small
                )
                .niceClickable(onClick = { chooseColor(color) })
                .padding(AppTheme.dimensions.tiniest),
            contentAlignment = Alignment.Center
        ) {
            SimpleImage(
                modifier = Modifier.clip(AppTheme.shapes.tiny),
                url = { color.image },
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
            items(state().colors) {
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
                text = state().color.name,
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
            state = {
                GoodsDetails(
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
                )
            }, chooseColor = {}
        )
    }
}
