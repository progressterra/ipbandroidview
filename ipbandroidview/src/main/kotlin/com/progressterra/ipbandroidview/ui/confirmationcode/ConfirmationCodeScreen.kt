package com.progressterra.ipbandroidview.ui.confirmationcode

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.composable.component.ConfirmationCodeComponent
import com.progressterra.ipbandroidview.composable.component.ConfirmationCodeComponentInteractor
import com.progressterra.ipbandroidview.composable.component.ConfirmationCodeComponentState
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ConfirmationCodeScreen(
    state: ConfirmationCodeComponentState,
    interactor: ConfirmationCodeComponentInteractor
) {
    ConfirmationCodeComponent(
        state = state,
        interactor = interactor
    )
}

@Preview
@Composable
private fun ConfirmationCodeScreenPreview() {
    AppTheme {
    }
}