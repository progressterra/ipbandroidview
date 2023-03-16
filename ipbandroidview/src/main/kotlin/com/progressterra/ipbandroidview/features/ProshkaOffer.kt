package com.progressterra.ipbandroidview.features

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.SimpleImage

@Immutable
data class ProshkaOfferState(
    val id: String = "",
    val title: String = "",
    val image: String = ""
)

sealed class ProshkaOfferEvent {

    object Clicked : ProshkaOfferEvent()
}

interface UseProshkaOffer {

    fun handleEvent(id: String, event: ProshkaOfferEvent)

    class Empty : UseProshkaOffer {
        override fun handleEvent(id: String, event: ProshkaOfferEvent) = Unit
    }
}

@Composable
fun ProshkaOffer(
    modifier: Modifier = Modifier,
    id: String = "default",
    state: ProshkaOfferState,
    useComponent: UseProshkaOffer = UseProshkaOffer.Empty()
) {
    Box(modifier = modifier
        .clip(RoundedCornerShape(8.dp))
        .niceClickable {
            useComponent.handleEvent(id, ProshkaOfferEvent.Clicked)
        }) {
        SimpleImage(
            url = state.image,
            backgroundColor = IpbTheme.colors.background.asColor()
        )
        BrushedText(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(8.dp),
            text = state.title,
            style = IpbTheme.typography.text,
            tint = IpbTheme.colors.textButton.asBrush()
        )
    }
}