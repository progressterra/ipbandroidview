package com.progressterra.ipbandroidview.features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.niceClickable


@Immutable
data class AuthProfileState(
    val id: String = "",
    val profileImage: String = ""
)

interface UseAuthProfile {

    fun handleEvent(id: String, event: AuthProfileEvent)
}

sealed class AuthProfileEvent {

    object Click : AuthProfileEvent()
}

@Composable
fun AuthProfile(
    modifier: Modifier = Modifier,
    state: AuthProfileState,
    useComponent: UseAuthProfile
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(IpbTheme.colors.surface1.asBrush())
            .niceClickable { useComponent.handleEvent(state.id, AuthProfileEvent.Click) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SimpleImage(
            url = state.profileImage,
            modifier = Modifier
                .clip(CircleShape)
                .size(80.dp),
            backgroundColor = IpbTheme.colors.surface1.asColor()
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BrushedText(
                text = stringResource(R.string.please_auth),
                style = IpbTheme.typography.title,
                tint = IpbTheme.colors.textSecondary.asBrush()
            )
            BrushedText(
                text = stringResource(R.string.please_auth),
                style = IpbTheme.typography.tertiaryText,
                tint = IpbTheme.colors.textTertiary1.asBrush()
            )
        }
        BrushedIcon(
            resId = R.drawable.ic_forw_pro,
            tint = IpbTheme.colors.iconPrimary1.asBrush()
        )
    }
}