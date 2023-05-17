package com.progressterra.ipbandroidview.features.interestspicker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InterestsPicker(
    modifier: Modifier = Modifier,
    state: InterestsPickerState = InterestsPickerState(),
    useComponent: UseInterestsPicker
) {

    @Composable
    fun Item(
        itemState: InterestsPickerState.Item
    ) {
        val backgroundBrush =
            if (itemState.selected) IpbTheme.colors.secondary.asBrush() else IpbTheme.colors.background.asBrush()
        Box(modifier = Modifier
            .padding(vertical = 4.dp)
            .clip(CircleShape)
            .background(backgroundBrush)
            .border(
                width = 2.dp, brush = IpbTheme.colors.secondary.asBrush(), shape = CircleShape
            )
            .niceClickable { useComponent.handle(InterestsPickerEvent.Select(itemState.id)) }
            .padding(horizontal = 16.dp, vertical = 8.dp)) {
            BrushedText(
                text = itemState.name,
                style = IpbTheme.typography.body,
                tint = IpbTheme.colors.textPrimary.asBrush()
            )
        }
    }

    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        state.items.forEach {
            Item(it)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun InterestsPickerPreview() {
    InterestsPicker(
        state = InterestsPickerState(
            items = listOf(
                InterestsPickerState.Item(
                    name = "coffee", selected = false
                ), InterestsPickerState.Item(
                    name = "sport", selected = true
                ), InterestsPickerState.Item(
                    name = "tea", selected = false
                ), InterestsPickerState.Item(
                    name = "lalala", selected = true
                ), InterestsPickerState.Item(
                    name = "some very long interest like a small dog psychology", selected = false
                ), InterestsPickerState.Item(
                    name = "armenian coffee", selected = false
                ), InterestsPickerState.Item(
                    name = "argentinian coffee", selected = true
                ), InterestsPickerState.Item(
                    name = "colombian coffee", selected = false
                )
            )
        ), useComponent = UseInterestsPicker.Empty()
    )
}