package com.progressterra.ipbandroidview.ui.confirmationcode

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.composable.component.ConfirmationCode
import com.progressterra.ipbandroidview.composable.component.ConfirmationCodeState
import com.progressterra.ipbandroidview.composable.component.UseConfirmationCode
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

/**
 * main - confirmation code
 */
@Composable
fun ConfirmationCodeScreen(
    state: ConfirmationCodeState,
    interactor: UseConfirmationCode
) {
    ConfirmationCode(
        id = "main",
        state = state,
        useComponent = interactor
    )
}

@Preview
@Composable
private fun ConfirmationCodeScreenPreview() {
    IpbTheme {
    }
}