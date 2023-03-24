package com.progressterra.ipbandroidview.pages.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import com.progressterra.ipbandroidview.features.AuthOrSkip
import com.progressterra.ipbandroidview.features.AuthOrSkipState
import com.progressterra.ipbandroidview.features.ProshkaTopBar
import com.progressterra.ipbandroidview.features.ProshkaTopBarState
import com.progressterra.ipbandroidview.features.UseAuthOrSkip
import com.progressterra.ipbandroidview.features.UseProshkaTopBar
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.widgets.ProshkaEditUser
import com.progressterra.ipbandroidview.widgets.ProshkaEditUserState
import com.progressterra.ipbandroidview.widgets.UseProshkaEditUser

@Immutable
data class SignUpState(
    val editUser: ProshkaEditUserState = ProshkaEditUserState(),
    val topBar: ProshkaTopBarState = ProshkaTopBarState(),
    val authOrSkip: AuthOrSkipState = AuthOrSkipState()
)

interface UseSignUp : UseProshkaTopBar, UseProshkaEditUser, UseAuthOrSkip

@Composable
fun SignUpScreen(
    state: SignUpState,
    useComponent: UseSignUp
) {
    ThemedLayout(
        topBar = {
            ProshkaTopBar(
                state = state.topBar,
                useComponent = useComponent
            )
        },
        bottomBar = {
            AuthOrSkip(
                state = state.authOrSkip,
                useComponent = useComponent
            )
        }
    ) { _, _ ->
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            ProshkaEditUser(
                state = state.editUser,
                useComponent = useComponent
            )
        }
    }
}