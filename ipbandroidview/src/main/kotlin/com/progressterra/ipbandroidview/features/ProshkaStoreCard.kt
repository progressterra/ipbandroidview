package com.progressterra.ipbandroidview.features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.composable.SimpleImage
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.model.SimplePrice

@Immutable
data class ProshkaStoreCardState(
    val name: String,
    val company: String,
    val price: SimplePrice,
    val imageUrl: String,
    val loan: String,
    val count: Int
)

sealed class ProshkaStoreCardEvent {

    object Open : ProshkaStoreCardEvent()

    object AddToCart : ProshkaStoreCardEvent()

    object RemoveFromCart : ProshkaStoreCardEvent()
}

interface UseProshkaStoreCard {

    fun handleEvent(id: String, event: ProshkaStoreCardEvent)

    class Empty : UseProshkaStoreCard {

        override fun handleEvent(id: String, event: ProshkaStoreCardEvent) = Unit
    }
}

@Composable
fun ProshkaStoreCard(
    modifier: Modifier = Modifier,
    id: String = "default",
    state: ProshkaStoreCardState,
    useComponent: UseProshkaStoreCard
) {
    Column(
        modifier = modifier.background(Brush.) .niceClickable {
            useComponent.handleEvent(
                id,
                ProshkaStoreCardEvent.Open
            )
        },
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        SimpleImage(
            modifier = Modifier.size(width = 157.dp, height = 122.dp),
            url = state.imageUrl,
            backgroundColor =
        )
    }
}