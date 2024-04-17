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
import com.progressterra.ipbandroidview.shared.ui.Icon
import com.progressterra.ipbandroidview.shared.ui.Image
import com.progressterra.ipbandroidview.shared.ui.Text
import com.progressterra.ipbandroidview.shared.ui.modifier.niceClickable

@Composable
fun EditProfile(
    modifier: Modifier = Modifier,
    state: EditProfileState,
    useComponent: UseEditProfile
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(IpbTheme.colors.surface.asBrush())
                .niceClickable(state.editing) { useComponent.handle(EditProfileEvent) }
                .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(modifier = Modifier.clip(CircleShape).size(80.dp), image = state.profileImage)
        if (state.editing) {
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = stringResource(id = R.string.change_avatar),
                style = IpbTheme.typography.subHeadlineBold,
                tint = IpbTheme.colors.textTertiary.asBrush()
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(resId = R.drawable.ic_edit, tint = IpbTheme.colors.textTertiary.asBrush())
        }
    }
}

@Composable
@Preview
private fun AuthProfilePreview() {
    IpbTheme { EditProfile(state = EditProfileState(), useComponent = UseEditProfile.Empty()) }
}
