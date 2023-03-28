package com.progressterra.ipbandroidview.pages.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.authorskip.AuthOrSkip
import com.progressterra.ipbandroidview.features.authorskip.AuthOrSkipState
import com.progressterra.ipbandroidview.features.proshkatopbar.ProshkaTopBar
import com.progressterra.ipbandroidview.features.authorskip.UseAuthOrSkip
import com.progressterra.ipbandroidview.features.proshkatopbar.UseProshkaTopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

@Immutable
data class SignInState(
    val phone: TextFieldState = TextFieldState(),
    val topBarState: ProshkaTopBarState = ProshkaTopBarState(),
    val authOrSkipState: AuthOrSkipState = AuthOrSkipState()
)

interface SignInInteractor : UseAuthOrSkip, UseTextField, UseProshkaTopBar

@Composable
fun SignInScreen(
    state: SignInState,
    interactor: SignInInteractor
) {
    ThemedLayout(
        topBar = {
            ProshkaTopBar(
                state = state.topBarState,
                useComponent = interactor
            )
        }
    ) { _, _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, start = 16.dp, end = 16.dp)
        ) {
            TextField(
                state = state.phone,
                useComponent = interactor
            )
            Spacer(Modifier.height(20.dp))
            BrushedText(
                text = stringResource(R.string.sign_in_text),
                textAlign = TextAlign.Center,
                style = IpbTheme.typography.tertiary,
                tint = IpbTheme.colors.textDisabled.asBrush()
            )
            Spacer(Modifier.weight(1f))
            AuthOrSkip(
                state = state.authOrSkipState,
                useComponent = interactor
            )
        }
    }
}