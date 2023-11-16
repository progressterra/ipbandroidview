package com.progressterra.ipbandroidview.features.addresssuggestions

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidapi.api.suggestion.model.SuggestionExtendedInfo
import com.progressterra.ipbandroidview.entities.SuggestionUI
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.modifier.niceClickable

@Composable
fun AddressSuggestions(
    modifier: Modifier = Modifier,
    state: AddressSuggestionsState,
    useComponent: UseAddressSuggestions
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = state.isVisible,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        Card(
            elevation = 4.dp, shape = RoundedCornerShape(8.dp),
        ) {
            LazyColumn(modifier = Modifier.heightIn(max = 150.dp)) {
                items(state.suggestions) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .niceClickable { useComponent.handle(AddressSuggestionsEvent(it)) }
                            .padding(
                                horizontal = 8.dp,
                                vertical = 6.dp
                            )
                    ) {
                        BrushedText(
                            text = it.previewOfSuggestion,
                            tint = IpbTheme.colors.textPrimary.asBrush(),
                            style = IpbTheme.typography.subHeadlineRegular
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun AddressSuggestionsPreview() {
    IpbTheme {
        Column(
            Modifier
                .background(Color.White)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AddressSuggestions(
                state = AddressSuggestionsState(
                    isVisible = true,
                    suggestions = listOf(
                        SuggestionUI(
                            previewOfSuggestion = "Lalala",
                            suggestionExtendedInfo = SuggestionExtendedInfo()
                        ),
                        SuggestionUI(
                            previewOfSuggestion = "Estoy triste",
                            suggestionExtendedInfo = SuggestionExtendedInfo()
                        ),
                        SuggestionUI(
                            previewOfSuggestion = "Estoy loco",
                            suggestionExtendedInfo = SuggestionExtendedInfo()
                        ),
                    )
                ), useComponent = UseAddressSuggestions.Empty()
            )
        }
    }
}