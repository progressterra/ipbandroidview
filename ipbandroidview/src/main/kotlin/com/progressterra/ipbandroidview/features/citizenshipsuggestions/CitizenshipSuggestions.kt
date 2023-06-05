package com.progressterra.ipbandroidview.features.citizenshipsuggestions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@Composable
fun CitizenshipSuggestions(
    modifier: Modifier = Modifier,
    state: CitizenshipSuggestionsState,
    useComponent: UseCitizenshipSuggestions
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(state.items) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .niceClickable { useComponent.handle(CitizenshipSuggestionsEvent.Click(it)) }
                .padding(horizontal = 16.dp, vertical = 4.dp)) {
                BrushedText(
                    text = it.name,
                    style = IpbTheme.typography.body,
                    tint = IpbTheme.colors.textPrimary.asBrush()
                )
            }
        }
    }
}

@Preview
@Composable
private fun SuggestionsPreview() {
    IpbTheme {
        CitizenshipSuggestions(
            state = CitizenshipSuggestionsState(
                items = listOf(
                    CitizenshipSuggestionsState.Item(
                        name = "Россия", data = ""
                    ), CitizenshipSuggestionsState.Item(
                        name = "Беларусь", data = ""
                    ), CitizenshipSuggestionsState.Item(
                        name = "Украина", data = ""
                    )
                )
            ), useComponent = UseCitizenshipSuggestions.Empty()
        )
    }
}
