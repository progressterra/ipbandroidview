package com.progressterra.ipbandroidview.ui.confirmationcode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.BottomHolder
import com.progressterra.ipbandroidview.composable.ThemedButton
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTextButton
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.VerificationCodeInput
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ConfirmationCodeScreen(
    state: ConfirmationCodeState,
    interactor: ConfirmationCodeInteractor
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(
            title = stringResource(id = R.string.verification_code),
            onBack = { interactor.onBack() }
        )
    }, bottomBar = {
        BottomHolder {
            ThemedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { interactor.onNext() },
                text = stringResource(id = R.string.next)
            )
            Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
            ThemedTextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { interactor.resend() },
                text = state.timer,
                enabled = state.canResend
            )
        }
    }) { _, _ ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(AppTheme.dimensions.small)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(AppTheme.shapes.medium)
                    .background(AppTheme.colors.surfaces)
                    .padding(AppTheme.dimensions.large)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${stringResource(id = R.string.verification_code_message)}\n${state.phoneNumber}",
                    color = AppTheme.colors.gray1,
                    style = AppTheme.typography.text,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(AppTheme.dimensions.large))
                VerificationCodeInput(
                    modifier = Modifier.fillMaxWidth(),
                    code = state.code,
                    editCode = { interactor.editCode(it) }
                )
            }
        }
    }
}

@Preview
@Composable
private fun ConfirmationCodeScreenPreview() {
    AppTheme {
    }
}