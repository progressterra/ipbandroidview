package com.progressterra.ipbandroidview.features.avatar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@Composable
fun Avatar(
    modifier: Modifier = Modifier, state: AvatarState, useComponent: UseAvatar
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        BrushedIcon(
            modifier = Modifier.size(79.dp),
            resId = R.drawable.avatar_background,
            tint = IpbTheme.colors.primary.asBrush()
        )
        SimpleImage(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .niceClickable { useComponent.handle(AvatarEvent(state.id)) },
            image = state.url,
            backgroundColor = IpbTheme.colors.background.asColor()
        )
        if (state.online) {
            BrushedIcon(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 4.dp, bottom = 9.dp),
                resId = R.drawable.ic_online,
                tint = IpbTheme.colors.primary.asBrush()
            )
        }
    }
}

@Preview
@Composable
private fun AvatarPreview() {
    Avatar(
        state = AvatarState(online = true),
        useComponent = UseAvatar.Empty()
    )
}

@Preview
@Composable
private fun AvatarPreviewOffline() {
    Avatar(
        state = AvatarState(),
        useComponent = UseAvatar.Empty()
    )
}