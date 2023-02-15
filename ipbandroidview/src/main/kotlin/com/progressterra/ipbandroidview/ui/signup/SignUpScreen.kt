package com.progressterra.ipbandroidview.ui.signup

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.composable.component.SignUpComponent
import com.progressterra.ipbandroidview.composable.component.SignUpComponentInteractor
import com.progressterra.ipbandroidview.composable.component.SignUpComponentState
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun SignUpScreen(
    state: SignUpComponentState,
    interactor: SignUpComponentInteractor
) {
    SignUpComponent(
        state = state,
        interactor = interactor
    )
}

@Preview
@Composable
private fun SignUpScreenPreviewEmpty() {
    AppTheme {
    }
}