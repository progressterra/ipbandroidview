package com.progressterra.ipbandroidview.features.profilebutton

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.pages.profile.ProfileScreenState
import com.progressterra.ipbandroidview.shared.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.modifier.niceClickable


@Composable
fun ProfileButton(
    modifier: Modifier = Modifier,
    state: ProfileButtonState,
    title: String,
    isDanger: Boolean = false,
    notification: ProfileScreenState.CounterNotification? = null,
    useComponent: UseProfileButton
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(IpbTheme.colors.surface.asBrush())
            .niceClickable(state.enabled) { useComponent.handle(ProfileButtonEvent(state.id)) }
            .then(
                if (IpbAndroidViewSettings.PROFILE_BUTTONS_BORDER) {
                    Modifier.border(
                        width = 2.dp,
                        brush = IpbTheme.colors.onSurface2.asBrush(),
                        shape = RoundedCornerShape(8.dp)
                    )
                } else {
                    Modifier
                }
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val color = if (state.enabled) {
            if (isDanger) {
                IpbTheme.colors.textPrimary2.asBrush()
            } else {
                IpbTheme.colors.textPrimary.asBrush()
            }
        } else {
            IpbTheme.colors.textDisabled.asBrush()
        }
        BrushedText(
            text = title,
            style = IpbTheme.typography.body,
            tint = color
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (notification?.isEmpty() == false) {
                BrushedText(
                    text = "${notification.count}/${notification.max}",
                    style = IpbTheme.typography.footnoteBold,
                    tint = if (notification.isFull()) IpbTheme.colors.onBackground.asBrush() else IpbTheme.colors.textPrimary2.asBrush()
                )
            }
            BrushedIcon(
                resId = R.drawable.ic_forw,
                tint = color
            )
        }
    }
}