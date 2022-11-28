package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.element.AddItemIcon
import com.progressterra.ipbandroidview.composable.element.RemoveItemIcon
import com.progressterra.ipbandroidview.composable.element.ThemedButton
import com.progressterra.ipbandroidview.composable.utils.SideBorder
import com.progressterra.ipbandroidview.composable.utils.sideBorder
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.GoodsDetails
import com.progressterra.ipbandroidview.theme.AppTheme

private val lineWidth = 0.5.dp


@Composable
fun GoodsBottomBar(
    modifier: Modifier = Modifier,
    state: () -> GoodsDetails,
    screenState: () -> ScreenState,
    onAdd: () -> Unit,
    onRemove: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(AppTheme.colors.surfaces)
            .sideBorder(top = SideBorder(lineWidth, AppTheme.colors.gray2))
            .padding(AppTheme.dimensions.large),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = state().price.toString(),
            style = AppTheme.typography.price,
            color = AppTheme.colors.black
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

@Preview
@Composable
fun GoodsBottomBarPreview() {
    AppTheme {
        GoodsBottomBar(
            state = { GoodsDetails() },
            screenState = { ScreenState.SUCCESS },
            onRemove = {}, onAdd = {}
        )
    }
}