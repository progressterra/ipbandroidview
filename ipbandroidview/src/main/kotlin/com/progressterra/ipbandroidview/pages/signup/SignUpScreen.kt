package com.progressterra.ipbandroidview.pages.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.authorskip.AuthOrSkipState
import com.progressterra.ipbandroidview.features.authorskip.NextOrSkip
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.widgets.edituser.EditUser
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState

@Composable
fun SignUpScreen(
    state: SignUpState, useComponent: UseSignUp
) {
    ThemedLayout(topBar = {
        TopBar(
            title = stringResource(R.string.sign_up),
            useComponent = useComponent,
            showBackButton = true
        )
    }, bottomBar = {
        NextOrSkip(
            state = state.authOrSkip, useComponent = useComponent
        )
    }, bottomOverlap = true) { _, bottom ->
        Column(
            modifier = Modifier
                .padding(top = 8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            EditUser(
                state = state.editUser, useComponent = useComponent
            )
            Spacer(Modifier.height(bottom))
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen(
        state = SignUpState(
            editUser = EditUserState(
                name = TextFieldState(
                    id = "mattis", text = "integer"
                ), email = TextFieldState(
                    id = "consetetur", text = "saepe"
                ), phone = TextFieldState(
                    id = "ad", text = "tellus"
                ), birthday = TextFieldState(
                    id = "voluptatibus", text = "mutat"
                ), citizenship = TextFieldState(
                    id = "vituperata", text = "vis"
                ), address = TextFieldState(
                    id = "an", text = "quaerendum"
                ), passport = TextFieldState(
                    id = "eruditi", text = "ullamcorper"
                ), passportProvider = TextFieldState(
                    id = "invidunt", text = "blandit"
                ), passportProviderCode = TextFieldState(
                    id = "parturient", text = "mandamus"
                ), patent = TextFieldState(
                    id = "eloquentiam", text = "persius"
                )
            ), authOrSkip = AuthOrSkipState(
                auth = ButtonState(id = "orci"), skip = ButtonState(id = "laudem")
            )
        ), useComponent = UseSignUp.Empty()
    )
}