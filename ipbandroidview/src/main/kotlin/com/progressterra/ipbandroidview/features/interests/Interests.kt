package com.progressterra.ipbandroidview.features.interests

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Interests(
    modifier: Modifier = Modifier,
    state: InterestsState = InterestsState(),
    useComponent: UseInterests
) {

    @Composable
    fun Item(
        itemState: InterestsState.Item
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
            .niceClickable { useComponent.handle(InterestsEvent.Select(itemState.id)) }
            .padding(horizontal = 16.dp, vertical = 8.dp)) {
            BrushedText(
                text = itemState.name,
                style = IpbTheme.typography.body,
                tint = IpbTheme.colors.textPrimary.asBrush()
            )
        }
    }

    Column(
        modifier = modifier.padding(horizontal = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        BrushedText(
            text = stringResource(R.string.interests),
            style = IpbTheme.typography.title,
            tint = IpbTheme.colors.textPrimary.asBrush()
        )
        val items = remember(state.editMode) {
            state.items.filter { it.selected || state.editMode }
        }
        FlowRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items.forEach {
                Item(it)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun InterestsPreview() {
    Interests(
        state = InterestsState(
            items = listOf(
                InterestsState.Item(
                    name = "coffee", selected = false
                ), InterestsState.Item(
                    name = "sport", selected = true
                ), InterestsState.Item(
                    name = "tea", selected = false
                ), InterestsState.Item(
                    name = "lalala", selected = true
                ), InterestsState.Item(
                    name = "some very long interest like a small dog psychology", selected = false
                ), InterestsState.Item(
                    name = "armenian coffee", selected = false
                ), InterestsState.Item(
                    name = "argentinian coffee", selected = true
                ), InterestsState.Item(
                    name = "colombian coffee", selected = false
                )
            )
        ),
        useComponent = UseInterests.Empty()
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun InterestsEditPreview() {
    Interests(
        state = InterestsState(
            items = listOf(
                InterestsState.Item(
                    name = "coffee", selected = false
                ), InterestsState.Item(
                    name = "sport", selected = true
                ), InterestsState.Item(
                    name = "tea", selected = false
                ), InterestsState.Item(
                    name = "lalala", selected = true
                ), InterestsState.Item(
                    name = "some very long interest like a small dog psychology", selected = false
                ), InterestsState.Item(
                    name = "armenian coffee", selected = false
                ), InterestsState.Item(
                    name = "argentinian coffee", selected = true
                ), InterestsState.Item(
                    name = "colombian coffee", selected = false
                )
            ),
            editMode = true
        ),
        useComponent = UseInterests.Empty()
    )
}