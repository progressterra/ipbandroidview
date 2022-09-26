package com.progressterra.ipbandroidview.confirmationcode

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
import com.progressterra.ipbandroidview.theme.AppTheme
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.ThemedButton
import com.progressterra.ipbandroidview.composable.ThemedTextButton
import com.progressterra.ipbandroidview.composable.TopAppBarWithBackNav
import com.progressterra.ipbandroidview.composable.VerificationCodeInput

@Composable
fun ConfirmationCodeScreen(state: ConfirmationCodeState, interactor: ConfirmationCodeInteractor) {
    Scaffold(topBar = {
        TopAppBarWithBackNav(title = stringResource(id = R.string.verification_code),
            onBack = { interactor.onBack() })
    }) {
        Surface(modifier = Modifier.fillMaxSize(), color = AppTheme.colors.background) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(
                        start = AppTheme.dimensions.small,
                        top = AppTheme.dimensions.small,
                        end = AppTheme.dimensions.small
                    ), verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(AppTheme.dimensions.normal))
                        .background(AppTheme.colors.surfaces)
                        .padding(AppTheme.dimensions.medium)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "${stringResource(id = R.string.verification_code_message)}\n${state.phoneNumber}",
                        color = AppTheme.colors.gray1,
                        style = AppTheme.typography.text,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.size(AppTheme.dimensions.medium))
                    VerificationCodeInput(modifier = Modifier.fillMaxWidth(),
                        code = state.code,
                        onCode = { interactor.onCode(it) })
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(AppTheme.dimensions.large))
                        .background(AppTheme.colors.surfaces)
                        .padding(AppTheme.dimensions.small)
                ) {
                    ThemedButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { interactor.onNext() },
                        text = stringResource(id = R.string.next)
                    )
                    Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
                    ThemedTextButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { interactor.onResend() },
                        text = if (state.isTimer) state.timer else stringResource(id = R.string.resend),
                        enabled = !state.isTimer
                    )
                    Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
                }
            }
        }
    }
}

@Preview
@Composable
fun ConfirmationCodeScreenPreview() {
    AppTheme {
        ConfirmationCodeScreen(ConfirmationCodeState(), ConfirmationCodeInteractor.Empty())
    }
}