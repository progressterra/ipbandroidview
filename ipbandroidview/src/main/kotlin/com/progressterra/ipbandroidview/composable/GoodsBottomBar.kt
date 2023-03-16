package com.progressterra.ipbandroidview.composable

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
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.composable.component.Button
import com.progressterra.ipbandroidview.composable.component.ButtonState
import com.progressterra.ipbandroidview.composable.component.UseButton
import com.progressterra.ipbandroidview.composable.utils.SideBorder
import com.progressterra.ipbandroidview.composable.utils.sideBorder
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

private val lineWidth = 0.5.dp

data class GoodsBottomBarComponentState(
    val inCartCounter: Int = 0,
    val price: String = "",
    val addButtonState: ButtonState = ButtonState()
)

interface UseGoodsBottomBar : UseButton {
    fun handleEvent(id: String, event: GoodsBottomBarEvent)
}

sealed class GoodsBottomBarEvent {
    object Add : GoodsBottomBarEvent()
    object Remove : GoodsBottomBarEvent()
}

/**
 * add - button component
 */
@Composable
fun GoodsBottomBar(
    modifier: Modifier = Modifier,
    id: String,
    state: GoodsBottomBarComponentState,
    useComponent: UseGoodsBottomBar
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(IpbTheme.colors.surfaces)
            .sideBorder(top = SideBorder(lineWidth, IpbTheme.colors.gray2))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = state.price, style = IpbTheme.typography.price, color = IpbTheme.colors.black
        )
        if (state.inCartCounter >= 1) Row(
            modifier = Modifier
                .clip(IpbTheme.shapes.button)
                .background(IpbTheme.colors.background),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { useComponent.handleEvent(id, GoodsBottomBarEvent.Remove) }) {
                RemoveItemIcon()
            }
            Text(
                text = state.inCartCounter.toString(),
                color = IpbTheme.colors.black,
                style = IpbTheme.typography.button
            )
            IconButton(onClick = { useComponent.handleEvent(id, GoodsBottomBarEvent.Add) }) {
                AddItemIcon(available = true)
            }
        }
        else Button(
            state = state.addButtonState, useComponent = useComponent, id = "add"
        )
    }
}