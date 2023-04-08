package com.progressterra.ipbandroidview.composable

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
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.shared.ui.niceClickable
import com.progressterra.ipbandroidview.entities.SuggestionUI
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

private val elevation = 4.dp

private val height = 150.dp

@Composable
fun AddressSuggestions(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    suggestions: List<SuggestionUI>,
    onSuggestion: (SuggestionUI) -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = isVisible,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        Card(
            elevation = elevation, shape = IpbTheme.shapes.small
        ) {
            LazyColumn(modifier = Modifier.heightIn(max = height)) {
                items(suggestions) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .niceClickable { onSuggestion(it) }
                            .padding(
                                horizontal = 8.dp,
                                vertical = 6.dp
                            )
                    ) {
                        Text(
                            text = it.previewOfSuggestion,
                            color = IpbTheme.colors.black,
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
                isVisible = true,
                suggestions = emptyList(),
                onSuggestion = {}
            )
        }
    }
}