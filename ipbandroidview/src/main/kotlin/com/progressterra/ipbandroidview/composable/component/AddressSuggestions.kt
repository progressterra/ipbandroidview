package com.progressterra.ipbandroidview.composable.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.theme.AppTheme
import com.progressterra.ipbandroidview.ui.city.Suggestion

private val elevation = 4.dp

@Composable
fun AddressSuggestions(
    modifier: Modifier = Modifier,
    isVisible: () -> Boolean,
    suggestions: () -> List<Suggestion>,
    onSuggestion: (Suggestion) -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = isVisible(),
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        Card(
            elevation = elevation, shape = AppTheme.shapes.small
        ) {
            LazyColumn {
                items(suggestions()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .niceClickable(onClick = { onSuggestion(it) })
                            .padding(
                                horizontal = AppTheme.dimensions.small,
                                vertical = AppTheme.dimensions.smany
                            ),
                        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiniest)
                    ) {
                        Text(
                            text = it.address,
                            color = AppTheme.colors.black,
                            style = AppTheme.typography.secondaryText
                        )
                        Text(
                            text = it.city,
                            color = AppTheme.colors.gray2,
                            style = AppTheme.typography.tertiaryText
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
    AppTheme {
        Column(
            Modifier
                .background(Color.White)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AddressSuggestions(
                isVisible = { true },
                suggestions = {
                    listOf(
                        Suggestion("Some address 1", "Some city 1"),
                        Suggestion("Some address 2", "Some city 2"),
                        Suggestion("Some address 3", "Some city 3")
                    )
                },
                onSuggestion = {}
            )
        }
    }
}