package com.progressterra.ipbandroidview.features.avatarpicker

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@Composable
fun AvatarPicker(
    modifier: Modifier = Modifier, state: AvatarPickerState, useComponent: UseAvatarPicker
) {

    @Composable
    fun Item(
        itemState: AvatarPickerState.Item
    ) {
        val borderModifier = if (itemState.selected) Modifier.border(
            width = 1.dp, brush = IpbTheme.colors.primary.asBrush(), shape = CircleShape
        ) else Modifier
        Box(
            modifier = borderModifier.padding(4.dp)
        ) {
            SimpleImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(80.dp)
                    .niceClickable { useComponent.handle(AvatarPickerEvent(itemState.id)) },
                image = itemState.url,
                backgroundColor = IpbTheme.colors.background.asColor()
            )
        }
    }

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        items(state.items) {
            Box(contentAlignment = Alignment.Center) {
                Item(it)
            }
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
private fun AvatarPickerPreview() {
    AvatarPicker(
        state = AvatarPickerState(
            items = listOf(
                AvatarPickerState.Item(selected = false),
                AvatarPickerState.Item(selected = false),
                AvatarPickerState.Item(selected = false),
                AvatarPickerState.Item(selected = true),
                AvatarPickerState.Item(selected = false),
                AvatarPickerState.Item(selected = false),
                AvatarPickerState.Item(selected = false),
                AvatarPickerState.Item(selected = false)
            )
        ), useComponent = UseAvatarPicker.Empty()
    )
}