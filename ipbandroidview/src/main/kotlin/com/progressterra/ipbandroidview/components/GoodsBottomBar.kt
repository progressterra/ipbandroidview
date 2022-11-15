package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.AddItemIcon
import com.progressterra.ipbandroidview.components.RemoveItemIcon
import com.progressterra.ipbandroidview.components.ThemedButton
import com.progressterra.ipbandroidview.components.utils.SideBorder
import com.progressterra.ipbandroidview.components.utils.sideBorder
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.component.InCartCounter
import com.progressterra.ipbandroidview.model.component.Price
import com.progressterra.ipbandroidview.theme.AppTheme

private val lineWidth = 0.5.dp

@Immutable
interface GoodsBottomBarState : InCartCounter, Price

@Composable
fun GoodsBottomBar(
    modifier: Modifier = Modifier,
    state: () -> GoodsBottomBarState,
    screenState: () -> ScreenState,
    onAdd: () -> Unit,
    onRemove: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(AppTheme.colors.surfaces)
            .sideBorder(top = SideBorder(lineWidth, AppTheme.colors.gray2))
            .padding(
                start = AppTheme.dimensions.large,
                top = AppTheme.dimensions.large,
                end = AppTheme.dimensions.large,
                bottom = AppTheme.dimensions.small
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = state().price, style = AppTheme.typography.price, color = AppTheme.colors.black
        )
        if (state().inCartCounter >= 1)
            Row(
                modifier = Modifier
                    .clip(AppTheme.shapes.button)
                    .background(AppTheme.colors.background),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiny),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onRemove) {
                    RemoveItemIcon()
                }
                Text(
                    text = state().inCartCounter.toString(),
                    color = AppTheme.colors.black,
                    style = AppTheme.typography.button
                )
                IconButton(onClick = onAdd) {
                    AddItemIcon(available = { true })
                }
            }
        else
            ThemedButton(
                onClick = onAdd,
                enabled = screenState()::isSuccess,
                text = stringResource(id = R.string.in_cart)
            )
    }
}