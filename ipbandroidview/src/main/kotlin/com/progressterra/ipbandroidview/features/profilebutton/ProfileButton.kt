package com.progressterra.ipbandroidview.features.profilebutton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.niceClickable


sealed class ProfileButtonEvent {

    object Click : ProfileButtonEvent()
}

@Composable
fun ProfileButton(
    modifier: Modifier = Modifier,
    state: ProfileButtonState,
    title: String,
    isDanger: Boolean = false,
    useComponent: UseProfileButton
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(IpbTheme.colors.surface1.asBrush())
            .niceClickable { useComponent.handle(ProfileButtonEvent.Click) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val color =
            if (isDanger) IpbTheme.colors.textPrimary2.asBrush() else IpbTheme.colors.textPrimary1.asBrush()
        BrushedText(
            text = title,
            style = IpbTheme.typography.body,
            tint = color
        )
        BrushedIcon(
            resId = R.drawable.ic_forw_pro,
            tint = color
        )
    }
}