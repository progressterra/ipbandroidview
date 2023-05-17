package com.progressterra.ipbandroidview.widgets.messages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Composable
fun Messages(
    modifier: Modifier = Modifier,
    state: MessagesState
) {

    @Composable
    fun Item(
        modifier: Modifier = Modifier,
        itemState: MessagesState.Item
    ) {
        val paddingValues = PaddingValues(
            start = if (itemState.user) 40.dp else 20.dp,
            end = if (itemState.user) 20.dp else 40.dp
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(paddingValues),
            horizontalArrangement = if (itemState.user) Arrangement.End else Arrangement.Start
        ) {
            Column(
                modifier = modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(IpbTheme.colors.surface.asBrush())
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                BrushedText(
                    text = itemState.content,
                    tint = IpbTheme.colors.textPrimary.asBrush(),
                    style = IpbTheme.typography.body
                )
                BrushedText(
                    text = itemState.date,
                    tint = IpbTheme.colors.textTertiary.asBrush(),
                    style = IpbTheme.typography.footnoteRegular
                )
            }
        }
    }

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(state.items) {
            Item(itemState = it)
        }
    }
}

@Composable
@Preview
private fun MessagesPreview() {
    Messages(
        state = MessagesState(
            items = listOf(
                MessagesState.Item(
                    user = true,
                    content = "Hello",
                    date = "11.11.1111"
                ),
                MessagesState.Item(
                    user = false,
                    content = "Hihihi",
                    date = "11.11.1111"
                ),
                MessagesState.Item(
                    user = false,
                    content = "Hahaha",
                    date = "11.11.1111"
                ),
                MessagesState.Item(
                    user = true,
                    content = "Gimme my order now!",
                    date = "11.11.1111"
                ),
                MessagesState.Item(
                    user = false,
                    content = "Hello, it is unavailable!",
                    date = "11.11.1111"
                )
            )
        )
    )
}