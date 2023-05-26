package com.progressterra.ipbandroidview.features.interestsdiff

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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InterestsDiff(
    modifier: Modifier = Modifier,
    state: InterestsDiffState = InterestsDiffState()
) {

    @Composable
    fun Item(
        itemState: InterestsDiffState.Item
    ) {
        val backgroundBrush =
            if (itemState.match) IpbTheme.colors.secondary.asBrush() else IpbTheme.colors.secondary2.asBrush()
        Box(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .clip(CircleShape)
                .background(backgroundBrush)
                .border(
                    width = 2.dp, brush = IpbTheme.colors.secondary.asBrush(), shape = CircleShape
                )
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
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
        state.items.forEach {
            Item(it)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun InterestsDiffPreview() {
    InterestsDiff(
        state = InterestsDiffState(
            items = listOf(
                InterestsDiffState.Item(
                    name = "coffee", match = true
                ), InterestsDiffState.Item(
                    name = "sport", match = true
                ), InterestsDiffState.Item(
                    name = "tea", match = true
                ), InterestsDiffState.Item(
                    name = "lalala", match = true
                ), InterestsDiffState.Item(
                    name = "some very long interest like a small dog psychology", match = false
                ), InterestsDiffState.Item(
                    name = "armenian coffee", match = false
                ), InterestsDiffState.Item(
                    name = "argentinian coffee", match = false
                ), InterestsDiffState.Item(
                    name = "colombian coffee", match = false
                )
            )
        )
    )
}
