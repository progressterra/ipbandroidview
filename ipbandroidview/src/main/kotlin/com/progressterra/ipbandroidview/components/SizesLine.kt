package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.utils.niceClickable
import com.progressterra.ipbandroidview.dto.size.GoodsSize
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun SizesLine(
    modifier: Modifier = Modifier,
    currentSize: GoodsSize,
    sizes: List<GoodsSize>,
    onSize: (GoodsSize) -> Unit,
    onTable: () -> Unit
) {

    @Composable
    fun Item(state: GoodsSize) {
        Column(
            modifier = Modifier
                .clip(AppTheme.shapes.small)
                .border(
                    width = 1.dp,
                    color = if (state == currentSize) AppTheme.colors.primary else Color.Transparent,
                    AppTheme.shapes.small
                )
                .niceClickable({ onSize(state) })
                .padding(vertical = 4.dp, horizontal = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = state.primary,
                color = if (state.enabled) AppTheme.colors.black else AppTheme.colors.gray3,
                style = AppTheme.typography.title
            )
            state.secondary?.let {
                Text(
                    text = state.secondary,
                    color = if (state.enabled) AppTheme.colors.gray2 else AppTheme.colors.gray3,
                    style = AppTheme.typography.tertiaryText
                )
            }
        }
    }
    Column(
        modifier = modifier
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(sizes) {
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
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = stringResource(id = R.string.size),
                color = AppTheme.colors.gray2,
                style = AppTheme.typography.tertiaryText
            )
            Text(
                text = currentSize.primary,
                color = AppTheme.colors.gray1,
                style = AppTheme.typography.tertiaryText
            )
        }
    }
}

@Preview
@Composable
fun SizesLinePreview() {
    AppTheme {
        val current = GoodsSize(
            primary = "M", secondary = "36", enabled = true
        )
        SizesLine(sizes = listOf(
            current,
            GoodsSize(
                primary = "L", secondary = "38", enabled = true
            ),
            GoodsSize(
                primary = "XL", secondary = "55", enabled = false
            ),
            GoodsSize(
                primary = "XXL", secondary = "77", enabled = true
            ),
        ), onSize = {}, onTable = {}, currentSize = current
        )
    }
}
