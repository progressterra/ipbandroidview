package com.progressterra.ipbandroidview.features.proshkaoffer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.shared.ui.niceClickable
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.SimpleImage

@Composable
fun ProshkaOffer(
    modifier: Modifier = Modifier,
    state: ProshkaOfferState,
    useComponent: UseProshkaOffer = UseProshkaOffer.Empty()
) {
    Box(modifier = modifier
        .clip(RoundedCornerShape(8.dp))
        .niceClickable {
            useComponent.handle(state.id, ProshkaOfferEvent.Clicked)
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
            style = IpbTheme.typography.primary,
            tint = IpbTheme.colors.textButton.asBrush()
        )
    }
}