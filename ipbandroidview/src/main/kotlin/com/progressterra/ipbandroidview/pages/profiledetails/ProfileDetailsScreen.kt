package com.progressterra.ipbandroidview.pages.profiledetails

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.editbutton.EditButton
import com.progressterra.ipbandroidview.features.editprofile.EditProfile
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn
import com.progressterra.ipbandroidview.widgets.edituser.EditUser

@Composable
fun ProfileDetailsScreen(
    modifier: Modifier = Modifier,
    state: ProfileDetailsState,
    useComponent: UseProfileDetailsScreen
) {
    ThemedLayout(
        modifier = modifier,
        topBar = {
            TopBar(
                title = stringResource(R.string.profile),
                useComponent = useComponent,
                showBackButton = IpbAndroidViewSettings.SHOW_PROFILE_DETAILS_BACK_BUTTON
            )
        },
        bottomBar = {
            EditButton(
                modifier = Modifier.padding(horizontal = 8.dp),
                state = state.editButton,
                useComponent = useComponent
            )
        },
        bottomOverlap = true
    ) { _, bottom ->
        StateColumn(
            scrollable = true,
            state = state.screen,
            useComponent = useComponent,
            horizontalAlignment = Alignment.Start
        ) {
            EditProfile(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp),
                state = state.editProfile,
                useComponent = useComponent
            )
            Spacer(Modifier.height(8.dp))
            EditUser(
                modifier = Modifier.padding(horizontal = 20.dp),
                state = state.editUser,
                useComponent = useComponent
            )
            Spacer(Modifier.height(bottom + 8.dp))
        }
    }
}
