package com.progressterra.ipbandroidview.ui.signin

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.composable.component.AuthComponent
import com.progressterra.ipbandroidview.composable.component.AuthComponentState
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun SignInScreen(
    state: AuthComponentState,
    interactor: SignInInteractor,
    canBeSkipped: Boolean,
    offerUrl: String,
    policyUrl: String
) {
    AuthComponent(
        modifier = Modifier.fillMaxSize(),
        state = state,
        interactor = interactor,
        canBeSkipped = canBeSkipped,
        offerUrl = offerUrl,
        policyUrl = policyUrl,
        prefix = "+"
    )
}

@Preview
@Composable
private fun SplashScreenPreview() {
    AppTheme {
    }
}

