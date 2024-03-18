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
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.modifier.niceClickable

@Composable
fun AuthProfile(
    modifier: Modifier = Modifier,
    state: AuthProfileState,
    useComponent: UseAuthProfile
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(IpbTheme.colors.surface.asBrush())
            .niceClickable { useComponent.handle(AuthProfileEvent) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SimpleImage(
            modifier = Modifier
                .clip(CircleShape)
                .size(80.dp),
            image = state.profileImage
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            BrushedText(
                text = state.name,
                style = IpbTheme.typography.title,
                tint = IpbTheme.colors.textSecondary.asBrush()
            )
            BrushedText(
                text = state.email,
                style = IpbTheme.typography.footnoteRegular,
                tint = IpbTheme.colors.textTertiary.asBrush()
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                BrushedIcon(
                    modifier = Modifier.size(20.dp),
                    resId = R.drawable.ic_bonuses,
                    tint = IpbTheme.colors.secondary.asBrush()
                )
                BrushedText(
                    text = "${state.bonuses} ${
                        pluralStringResource(
                            id = R.plurals.bonuses,
                            state.bonuses
                        )
                    }",
                    style = IpbTheme.typography.caption,
                    tint = IpbTheme.colors.textPrimary2.asBrush()
                )
            }
            BrushedText(
                text = "${state.expiringBonuses} ${
                    pluralStringResource(
                        id = R.plurals.bonuses,
                        state.expiringBonuses
                    )
                } ${
                    pluralStringResource(
                        id = R.plurals.will_burn,
                        state.expiringBonuses
                    )
                } ${state.expiringDate}",
                style = IpbTheme.typography.caption,
                tint = IpbTheme.colors.textSecondary.asBrush()
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        BrushedIcon(
            resId = R.drawable.ic_forw,
            tint = IpbTheme.colors.iconPrimary.asBrush()
        )
    }
}

@Composable
@Preview
private fun AuthProfilePreview() {
    IpbTheme {
        AuthProfile(
            state = AuthProfileState(
                name = "Volan de Mort",
                email = "email@email.email",
                bonuses = 250,
                expiringBonuses = 140,
                expiringDate = "12.12.2012"
            ),
            useComponent = UseAuthProfile.Empty()
        )
    }
}