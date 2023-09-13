package com.progressterra.ipbandroidview.features.editprofile

import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@Composable
fun EditProfile(
    modifier: Modifier = Modifier,
    state: EditProfileState,
    useComponent: UseEditProfile
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(IpbTheme.colors.surface.asBrush())
            .niceClickable { useComponent.handle(EditProfileEvent) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SimpleImage(
            image = state.profileImage,
            modifier = Modifier
                .clip(CircleShape)
                .size(80.dp),
            backgroundColor = IpbTheme.colors.surface.asColor()
        )
        Spacer(modifier = Modifier.width(20.dp))
        BrushedText(
            text = stringResource(id = R.string.change_avatar),
            style = IpbTheme.typography.subHeadlineBold,
            tint = IpbTheme.colors.textTertiary.asBrush()
        )
        BrushedIcon(
            resId = R.drawable.ic_edit,
            tint = IpbTheme.colors.textTertiary.asBrush()
        )
    }
}

@Composable
@Preview
private fun AuthProfilePreview() {
    IpbTheme {
        EditProfile(
            state = EditProfileState(),
            useComponent = UseEditProfile.Empty()
        )
    }
}