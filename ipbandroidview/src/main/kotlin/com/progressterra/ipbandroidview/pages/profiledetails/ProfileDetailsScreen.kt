package com.progressterra.ipbandroidview.pages.profiledetails

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.editbutton.EditButton
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumn
import com.progressterra.ipbandroidview.widgets.edituser.EditUser

@Composable
fun ProfileDetailsScreen(
    state: ProfileDetailsState,
    useComponent: UseProfileDetails
) {
    ThemedLayout(
        topBar = {
            TopBar(
                title = stringResource(R.string.profile),
                useComponent = useComponent,
                showBackButton = true
            )
        },
        bottomBar = {
            EditButton(state = state.editButton, useComponent = useComponent)
        }, bottomOverlap = true
    ) { _, bottom ->
        StateColumn(
            scrollable = true,
            state = state.screen,
            useComponent = useComponent
        ) {
            EditUser(
                modifier = Modifier.padding(vertical = 8.dp),
                state = state.editUser,
                useComponent = useComponent
            )
            Spacer(Modifier.height(bottom))
        }
    }
}
