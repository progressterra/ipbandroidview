package com.progressterra.ipbandroidview.pages.profile

import androidx.compose.foundation.layout.Column
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
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox

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
        StateBox(state = state.screenState, useComponent = useComponent) {
            Column(Modifier.padding(top = 8.dp)) {
                if (state.isAuthorized) {
                    AuthProfile(
                        state = state.authProfileState,
                        useComponent = useComponent
                    )
                } else {
                    UnAuthPlaceholder(
                        state = state.unAuthProfileState,
                        useComponent = useComponent
                    )
                }
                Spacer(Modifier.height(8.dp))
                ProfileButton(
                    state = state.orders,
                    useComponent = useComponent,
                    title = stringResource(R.string.my_orders)
                )
                Spacer(Modifier.height(8.dp))
                ProfileButton(
                    state = state.favorites,
                    useComponent = useComponent,
                    title = stringResource(R.string.favorites)
                )
                Spacer(Modifier.height(8.dp))
                ProfileButton(
                    state = state.support,
                    useComponent = useComponent,
                    title = stringResource(R.string.support)
                )
                Spacer(Modifier.weight(1f))
                ProfileButton(
                    state = state.deleteAccount,
                    useComponent = useComponent,
                    isDanger = true,
                    title = stringResource(R.string.delete_account)
                )
                Spacer(Modifier.height(8.dp))
                ProfileButton(
                    state = state.logout,
                    useComponent = useComponent,
                    title = stringResource(R.string.logout)
                )
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}