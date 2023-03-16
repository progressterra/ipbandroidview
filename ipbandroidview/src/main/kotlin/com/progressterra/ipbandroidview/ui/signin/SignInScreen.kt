package com.progressterra.ipbandroidview.ui.signin

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.composable.component.AuthComponent
import com.progressterra.ipbandroidview.composable.component.AuthState
import com.progressterra.ipbandroidview.composable.component.UseAuth
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

/**
 * main - auth
 */
@Composable
fun SignInScreen(
    state: AuthState,
    interactor: UseAuth,
    canBeSkipped: Boolean,
    offerUrl: String,
    policyUrl: String
) {
    AuthComponent(
        modifier = Modifier.fillMaxSize(),
        id = "main",
        state = state,
        useComponent = interactor,
        canBeSkipped = canBeSkipped,
        offerUrl = offerUrl,
        policyUrl = policyUrl
    )
}

@Preview
@Composable
private fun SplashScreenPreview() {
    IpbTheme {
    }
}

