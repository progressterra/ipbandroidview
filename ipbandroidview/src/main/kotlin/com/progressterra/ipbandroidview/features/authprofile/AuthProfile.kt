package com.progressterra.ipbandroidview.features.authprofile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
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


@Composable
fun AuthProfile(
    modifier: Modifier = Modifier,
    state: AuthProfileState,
    useComponent: UseAuthProfile
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(IpbTheme.colors.surface.asBrush())
            .niceClickable { useComponent.handle(AuthProfileEvent.Click) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SimpleImage(
            url = state.profileImage,
            modifier = Modifier
                .clip(CircleShape)
                .size(80.dp),
            backgroundColor = IpbTheme.colors.surface.asColor()
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
                style = IpbTheme.typography.footnoteRegular,
                tint = IpbTheme.colors.textTertiary.asBrush()
            )
        }
        BrushedIcon(
            resId = R.drawable.ic_forw_pro,
            tint = IpbTheme.colors.iconPrimary.asBrush()
        )
    }
}