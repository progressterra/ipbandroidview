package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.utils.niceClickable
import com.progressterra.ipbandroidview.model.GoodsSize
import com.progressterra.ipbandroidview.model.component.Size
import com.progressterra.ipbandroidview.model.component.Sizes
import com.progressterra.ipbandroidview.theme.AppTheme

@Immutable
interface SizesLineState : Size, Sizes

@Composable
fun SizesLine(
    modifier: Modifier = Modifier,
    state: () -> SizesLineState,
    onSize: (GoodsSize) -> Unit,
    onTable: () -> Unit
) {

    @Composable
    fun Item(size: GoodsSize) {
        Column(
            modifier = Modifier
                .clip(AppTheme.shapes.small)
                .border(
                    width = 1.dp,
                    color = if (size == state().size) AppTheme.colors.primary else Color.Transparent,
                    AppTheme.shapes.small
                )
                .niceClickable({ onSize(size) })
                .padding(
                    vertical = AppTheme.dimensions.tiny,
                    horizontal = AppTheme.dimensions.smany
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = size.primary,
                color = if (size.available) AppTheme.colors.black else AppTheme.colors.gray3,
                style = AppTheme.typography.title
            )
            size.secondary?.let {
                Text(
                    text = size.secondary,
                    color = if (size.available) AppTheme.colors.gray2 else AppTheme.colors.gray3,
                    style = AppTheme.typography.tertiaryText
                )
            }
        }
    }
    Column(
        modifier = modifier
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .padding(AppTheme.dimensions.medium),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiny)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)) {
                items(state().sizes) {
                    Item(it)
                }
            }
            IconButton(onClick = onTable) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    RulerIcon()
                    Text(
                        text = stringResource(id = R.string.table),
                        color = AppTheme.colors.primary,
                        style = AppTheme.typography.tertiaryText
                    )
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiny)) {
            Text(
                text = stringResource(id = R.string.size),
                color = AppTheme.colors.gray2,
                style = AppTheme.typography.tertiaryText
            )
            Text(
                text = state().size.primary,
                color = AppTheme.colors.gray1,
                style = AppTheme.typography.tertiaryText
            )
        }
    }
}

private class SizesLineStatePreview(
    override val size: GoodsSize,
    override val sizes: List<GoodsSize>
) : SizesLineState

@Preview
@Composable
private fun SizesLinePreview() {
    AppTheme {
        val current = GoodsSize(
            primary = "M", secondary = "36", available = true
        )
        SizesLine(state = {
            SizesLineStatePreview(
                sizes = listOf(
                    current,
                    GoodsSize(
                        primary = "L", secondary = "38", available = true
                    ),
                    GoodsSize(
                        primary = "XL", secondary = "55", available = false
                    ),
                    GoodsSize(
                        primary = "XXL", secondary = "77", available = true
                    ),
                ), size = current
            )
        }, onSize = {}, onTable = {})
    }
}