package com.progressterra.ipbandroidview.features.citizenshipsuggestions

import androidx.compose.foundation.layout.Row
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

@Composable
fun CitizenshipSuggestions(
    modifier: Modifier = Modifier,
    state: CitizenshipSuggestionsState,
    useComponent: UseCitizenshipSuggestions
) {
    if (!state.toHide && !state.exact) {
        Row(modifier = modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .niceClickable { useComponent.handle(CitizenshipSuggestionsEvent.Click) }
            .padding(horizontal = 16.dp, vertical = 4.dp)) {
            BrushedText(
                text = state.suggestion,
                style = IpbTheme.typography.body,
                tint = IpbTheme.colors.textPrimary.asBrush()
            )
        }
    }
}

@Preview
@Composable
private fun SuggestionsPreview() {
    IpbTheme {
        CitizenshipSuggestions(
            state = CitizenshipSuggestionsState(
                suggestion = "Belarus",
                id = "",
                toHide = false
            ), useComponent = UseCitizenshipSuggestions.Empty()
        )
    }
}
