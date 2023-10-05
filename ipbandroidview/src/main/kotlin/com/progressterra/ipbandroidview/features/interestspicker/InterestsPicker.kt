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
import com.progressterra.ipbandroidview.entities.Interest
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
        itemState: Interest
    ) {
        val backgroundBrush =
            if (itemState.picked || state.changedInterests.contains(itemState)) IpbTheme.colors.secondary.asBrush() else IpbTheme.colors.background.asBrush()
        Box(modifier = Modifier
            .padding(vertical = 4.dp)
            .clip(CircleShape)
            .background(backgroundBrush)
            .border(
                width = 2.dp, brush = IpbTheme.colors.secondary.asBrush(), shape = CircleShape
            )
            .niceClickable { useComponent.handle(InterestsPickerEvent(itemState)) }
            .padding(horizontal = 16.dp, vertical = 8.dp)) {
            BrushedText(
                text = itemState.name,
                style = IpbTheme.typography.body,
                tint = IpbTheme.colors.textPrimary.asBrush()
            )
        }
    }

    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        state.allInterests.forEach {
            Item(it)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun InterestsPickerPreview() {
    InterestsPicker(
        state = InterestsPickerState(
            allInterests =
            listOf(
                Interest(
                    id = "0",
                    name = "coffee", picked = false
                ), Interest(
                    id = "1",
                    name = "sport", picked = true
                ), Interest(
                    id = "2",
                    name = "tea", picked = false
                ), Interest(
                    id = "3",
                    name = "lalala", picked = true
                ), Interest(
                    id = "4",
                    name = "some very long interest like a small dog psychology",
                    picked = false
                ), Interest(
                    id = "5",
                    name = "armenian coffee", picked = false
                ), Interest(
                    id = "6",
                    name = "argentinian coffee", picked = true
                ), Interest(
                    id = "7",
                    name = "colombian coffee", picked = false
                )
            )
        ), useComponent = UseInterestsPicker.Empty()
    )
}