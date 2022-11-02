package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.utils.niceClickable
import com.progressterra.ipbandroidview.dto.size.GoodsSize
import com.progressterra.ipbandroidview.dto.size.GoodsSizeState
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun SizesLine(
    modifier: Modifier = Modifier,
    sizes: List<GoodsSize>,
    onSize: (GoodsSize) -> Unit,
    onTable: () -> Unit
) {

    @Composable
    fun Item(state: GoodsSize) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(AppTheme.roundings.smallRounding))
                .border(
                    width = 1.dp,
                    color = if (state.state == GoodsSizeState.SELECTED) AppTheme.colors.primary else Color.Transparent,
                    RoundedCornerShape(AppTheme.roundings.smallRounding)
                )
                .niceClickable({ onSize(state) }),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = state.primary,
                color = if (state.state == GoodsSizeState.DISABLED) AppTheme.colors.gray3 else AppTheme.colors.black,
                style = AppTheme.typography.title
            )
            state.secondary?.let {
                Text(
                    text = state.secondary,
                    color = if (state.state == GoodsSizeState.DISABLED) AppTheme.colors.gray3 else AppTheme.colors.gray2,
                    style = AppTheme.typography.tertiaryText
                )
            }
        }
    }
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(AppTheme.roundings.mediumRounding))
            .background(AppTheme.colors.surfaces)
            .padding(12.dp)
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
    }
}