package com.progressterra.ipbandroidview.ui.confirmationcode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.*
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ConfirmationCodeScreen(
    state: ConfirmationCodeState,
    interactor: ConfirmationCodeInteractor
) {
    Scaffold(topBar = {
        ThemedTopAppBar(
            title = stringResource(id = R.string.verification_code),
            onBack = { interactor.onBack() })
    }) {
        Surface(modifier = Modifier.fillMaxSize(), color = AppTheme.colors.background) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(
                        start = 8.dp,
                        top = 8.dp,
                        end = 8.dp
                    ), verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(AppTheme.dimensions.mediumRounding))
                        .background(AppTheme.colors.surfaces)
                        .padding(16.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "${stringResource(id = R.string.verification_code_message)}\n${state.phoneNumber}",
                        color = AppTheme.colors.gray1,
                        style = AppTheme.typography.text,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    VerificationCodeInput(modifier = Modifier.fillMaxWidth(),
                        code = state.code,
                        onCode = { interactor.onCode(it) })
                }
                BottomHolder {
                    ThemedButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { interactor.onNext() },
                        text = stringResource(id = R.string.next)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    ThemedTextButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { interactor.onResend() },
                        text = if (state.isTimer) state.timer else stringResource(id = R.string.resend),
                        enabled = !state.isTimer
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ConfirmationCodeScreenPreview() {
    AppTheme {
        ConfirmationCodeScreen(
            ConfirmationCodeState(
                phoneNumber = "+7 999 999 99 99", code = "123"
            ), ConfirmationCodeInteractor.Empty()
        )
    }
}