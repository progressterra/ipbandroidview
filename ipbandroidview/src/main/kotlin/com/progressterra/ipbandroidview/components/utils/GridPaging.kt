package com.progressterra.ipbandroidview.components.utils

import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems

fun <T : Any> LazyGridScope.items(
    items: LazyPagingItems<T>,
    key: ((item: T) -> Any),
    itemContent: @Composable LazyGridItemScope.(value: T?) -> Unit
) {
    items(count = items.itemCount, key = { index ->
        items[index]?.let(key) ?: index
    }) { index ->
        itemContent(items[index])
    }
}