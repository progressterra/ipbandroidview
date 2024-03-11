package com.progressterra.ipbandroidview.pages.profile

import androidx.compose.foundation.layout.Spacer
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
import com.progressterra.ipbandroidview.shared.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    state: ProfileScreenState,
    useComponent: UseProfileScreen
) {
    ThemedLayout(
        modifier = modifier,
        topBar = {
            TopBar(
                title = stringResource(R.string.profile),
                useComponent = useComponent
            )
        }
    ) { _, _ ->
        StateColumn(
            state = state.screen,
            useComponent = useComponent,
            scrollable = true
        ) {
            if (state.isAuthorized) {
                AuthProfile(
                    modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp),
                    state = state.authProfileState,
                    useComponent = useComponent
                )
            } else {
                UnAuthPlaceholder(
                    modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp),
                    state = state.unAuth,
                    useComponent = useComponent
                )
            }
            if (IpbAndroidViewSettings.PROFILE_BUTTONS.contains("orders")) {
                ProfileButton(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 8.dp),
                    state = state.orders,
                    useComponent = useComponent,
                    title = stringResource(R.string.my_orders)
                )
            }
            if (IpbAndroidViewSettings.PROFILE_BUTTONS.contains("wantThis")) {
                ProfileButton(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 8.dp),
                    state = state.wantThis,
                    useComponent = useComponent,
                    title = stringResource(R.string.want_this)
                )
            }
            if (IpbAndroidViewSettings.PROFILE_BUTTONS.contains("favorites")) {
                ProfileButton(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 8.dp),
                    state = state.favorites,
                    useComponent = useComponent,
                    title = stringResource(R.string.favorites)
                )
            }
            if (IpbAndroidViewSettings.PROFILE_BUTTONS.contains("documents")) {
                ProfileButton(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 8.dp),
                    state = state.documents,
                    useComponent = useComponent,
                    title = stringResource(R.string.documents),
                    notification = state.docNotification
                )
            }
            if (IpbAndroidViewSettings.PROFILE_BUTTONS.contains("bankCards")) {
                ProfileButton(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 8.dp),
                    state = state.bankCards,
                    useComponent = useComponent,
                    title = stringResource(R.string.bank_cards)
                )
            }
            if (IpbAndroidViewSettings.PROFILE_BUTTONS.contains("support")) {
                ProfileButton(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 8.dp),
                    state = state.support,
                    useComponent = useComponent,
                    title = stringResource(R.string.support)
                )
            }
            Spacer(Modifier.weight(1f))
            ProfileButton(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 8.dp, bottom = 36.dp),
                state = state.logout,
                useComponent = useComponent,
                title = stringResource(R.string.logout)
            )
        }
    }
}