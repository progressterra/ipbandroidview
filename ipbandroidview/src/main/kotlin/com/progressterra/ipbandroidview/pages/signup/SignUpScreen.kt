package com.progressterra.ipbandroidview.pages.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.authorskip.NextOrSkip
import com.progressterra.ipbandroidview.features.proshkatopbar.ProshkaTopBar
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.widgets.proshkaedituser.ProshkaEditUser

@Composable
fun SignUpScreen(
    state: SignUpState,
    useComponent: UseSignUp
) {
    ThemedLayout(
        topBar = {
            ProshkaTopBar(
                title = stringResource(R.string.sign_up),
                useComponent = useComponent
            )
        },
        bottomBar = {
            NextOrSkip(
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