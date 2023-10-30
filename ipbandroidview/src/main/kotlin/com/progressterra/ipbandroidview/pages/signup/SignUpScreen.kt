package com.progressterra.ipbandroidview.pages.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.button.TextButton
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.widgets.edituser.EditUser
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier, state: SignUpScreenState, useComponent: UseSignUpScreen
) {
    ThemedLayout(
        modifier = modifier,
        topBar = {
            TopBar(
                title = stringResource(R.string.sign_up),
                useComponent = useComponent,
                showBackButton = true
            )
        }, bottomBar = {
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(IpbTheme.colors.surface.asBrush())
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 36.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    state = state.next,
                    title = stringResource(R.string.next),
                    useComponent = useComponent
                )
                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    state = state.skip,
                    title = stringResource(R.string.auth_skip_button),
                    useComponent = useComponent
                )
            }
        }, bottomOverlap = true
    ) { _, bottom ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            EditUser(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp),
                state = state.editUser,
                useComponent = useComponent
            )
            Spacer(Modifier.height(bottom))
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen(
        state = SignUpScreenState(
            editUser = EditUserState(
                name = TextFieldState(
                    id = "mattis", text = "integer"
                ), email = TextFieldState(
                    id = "consetetur", text = "saepe"
                ), phone = TextFieldState(
                    id = "ad", text = "tellus"
                ), birthday = TextFieldState(
                    id = "voluptatibus", text = "mutat"
                )
            ),
            next = ButtonState(id = "orci"),
            skip = ButtonState(id = "laudem")
        ), useComponent = UseSignUpScreen.Empty()
    )
}