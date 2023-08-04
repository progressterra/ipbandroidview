package com.progressterra.ipbandroidview.pages.profile

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.authprofile.AuthProfile
import com.progressterra.ipbandroidview.features.profilebutton.ProfileButton
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.features.unauthplaceholder.UnAuthPlaceholder
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumn

@Composable
fun ProfileScreen(
    state: ProfileState,
    useComponent: UseProfile
) {
    ThemedLayout(
        topBar = {
            TopBar(
                title = stringResource(R.string.profile),
                useComponent = useComponent
            )
        }
    ) { _, _ ->
        StateColumn(state = state.screenState, useComponent = useComponent) {
            if (state.isAuthorized) {
                AuthProfile(
                    modifier = Modifier.padding(top = 8.dp, start = 20.dp, end = 20.dp),
                    state = state.authProfileState,
                    useComponent = useComponent
                )
            } else {
                UnAuthPlaceholder(
                    modifier = Modifier.padding(top = 8.dp, start = 20.dp, end = 20.dp),
                    state = state.unAuth,
                    useComponent = useComponent
                )
            }
            Spacer(Modifier.height(8.dp))
            ProfileButton(
                modifier = Modifier.padding(horizontal = 20.dp),
                state = state.orders,
                useComponent = useComponent,
                title = stringResource(R.string.my_orders)
            )
            Spacer(Modifier.height(8.dp))
            ProfileButton(
                modifier = Modifier.padding(horizontal = 20.dp),
                state = state.favorites,
                useComponent = useComponent,
                title = stringResource(R.string.favorites)
            )
            Spacer(Modifier.height(8.dp))
            ProfileButton(
                modifier = Modifier.padding(horizontal = 20.dp),
                state = state.documents,
                useComponent = useComponent,
                title = stringResource(R.string.documents),
                notification = state.docNotification
            )
            Spacer(Modifier.height(8.dp))
            ProfileButton(
                modifier = Modifier.padding(horizontal = 20.dp),
                state = state.support,
                useComponent = useComponent,
                title = stringResource(R.string.support)
            )
            Spacer(Modifier.weight(1f))
            ProfileButton(
                modifier = Modifier.padding(horizontal = 20.dp),
                state = state.logout,
                useComponent = useComponent,
                title = stringResource(R.string.logout)
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}