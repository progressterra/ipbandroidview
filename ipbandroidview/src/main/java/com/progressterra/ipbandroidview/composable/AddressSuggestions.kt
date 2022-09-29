package com.progressterra.ipbandroidview.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.theme.AppTheme
import com.progressterra.ipbandroidview.ui.city.Suggestion

private val cardElevation: Dp = 4.dp
private val cardRounding: Dp = 8.dp
private val itemHorizontalPadding: Dp = 8.dp
private val itemVerticalPadding: Dp = 6.dp
private val itemLineSpacing: Dp = 2.dp

@Composable
fun AddressSuggestions(
    modifier: Modifier = Modifier,
    suggestions: List<Suggestion> = emptyList(),
    isVisible: Boolean = false
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = isVisible,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        Card(
            elevation = cardElevation, shape = RoundedCornerShape(cardRounding)
        ) {
            LazyColumn {
                items(suggestions) {
                    Column(
                        modifier = Modifier
                            .padding(
                                horizontal = itemHorizontalPadding, vertical = itemVerticalPadding
                            )
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = it.address,
                            color = AppTheme.colors.black,
                            style = AppTheme.typography.secondaryText
                        )
                        Spacer(modifier = Modifier.size(itemLineSpacing))
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
                isVisible = true, suggestions = listOf(
                    Suggestion("Some address 1", "Some city 1"),
                    Suggestion("Some address 2", "Some city 2"),
                    Suggestion("Some address 3", "Some city 3")
                )
            )
        }

    }
}