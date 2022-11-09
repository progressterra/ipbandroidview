package com.progressterra.ipbandroidview.components.bottombar

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.AddItemIcon
import com.progressterra.ipbandroidview.components.RemoveItemIcon
import com.progressterra.ipbandroidview.components.ThemedButton
import com.progressterra.ipbandroidview.components.utils.SideBorder
import com.progressterra.ipbandroidview.components.utils.sideBorder
import com.progressterra.ipbandroidview.dto.component.InCartCounter
import com.progressterra.ipbandroidview.dto.component.Price
import com.progressterra.ipbandroidview.theme.AppTheme


//TODO add preview

interface GoodsBottomBarState : InCartCounter, Price


@Composable
fun GoodsBottomBar(
    modifier: Modifier = Modifier,
    state: GoodsBottomBarState,
    onAdd: () -> Unit,
    onRemove: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .background(AppTheme.colors.surfaces)
            .sideBorder(top = SideBorder(0.5.dp, AppTheme.colors.gray2))
            .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = state.price, style = TextStyle(
                color = AppTheme.colors.black,
                fontSize = 23.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 27.6.sp
            )
        )
        if (state.inCartCounter >= 1)
            Row(
                modifier = Modifier
                    .clip(AppTheme.shapes.button)
                    .background(AppTheme.colors.background),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onRemove) {
                    RemoveItemIcon()
                }
                Text(
                    text = state.inCartCounter.toString(),
                    color = AppTheme.colors.black,
                    style = AppTheme.typography.button
                )
                IconButton(onClick = onAdd) {
                    //TODO available
                    AddItemIcon(available = true)
                }
            }
        else
            ThemedButton(onClick = onAdd, text = stringResource(id = R.string.in_cart))
    }
}