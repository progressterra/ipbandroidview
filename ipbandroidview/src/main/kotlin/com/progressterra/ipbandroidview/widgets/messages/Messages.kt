package com.progressterra.ipbandroidview.widgets.messages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.progressterra.ipbandroidview.entities.Message
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Composable
fun Messages(
    modifier: Modifier = Modifier,
    state: MessagesState,
    messagesBackground: Brush
) {

    @Composable
    fun Item(
        modifier: Modifier = Modifier,
        itemState: Message
    ) {
        val paddingValues = PaddingValues(
            start = if (itemState.user) 40.dp else 0.dp,
            end = if (itemState.user) 0.dp else 40.dp
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
                    .background(messagesBackground)
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp),
                horizontalAlignment = if (itemState.user) Alignment.End else Alignment.Start
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

    val lazyItems = state.items.collectAsLazyPagingItems()
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(space = 8.dp, alignment = Alignment.Bottom),
        reverseLayout = true
    ) {
        items(
            count = lazyItems.itemCount,
            key = lazyItems.itemKey { it.id }
        ) { index ->
            lazyItems[index]?.let {
                Item(itemState = it)
            }
        }
    }
}