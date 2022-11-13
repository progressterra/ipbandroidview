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
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.BottomHolder
import com.progressterra.ipbandroidview.components.ThemedButton
import com.progressterra.ipbandroidview.components.ThemedLayout
import com.progressterra.ipbandroidview.components.ThemedTextButton
import com.progressterra.ipbandroidview.components.VerificationCodeInput
import com.progressterra.ipbandroidview.components.topbar.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ConfirmationCodeScreen(
    state: () -> ConfirmationCodeState,
    resend: () -> Unit,
    next: () -> Unit,
    editCode: (String) -> Unit,
    back: () -> Unit
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(
            title = { stringResource(id = R.string.verification_code) },
            onBack = back
        )
    }, bottomBar = {
        BottomHolder {
            ThemedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = next,
                text = { stringResource(id = R.string.next) }
            )
            Spacer(modifier = Modifier.size(8.dp))
            ThemedTextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = resend,
                text = { if (state().isTimer) state().timer else stringResource(id = R.string.resend) },
                enabled = { !state().isTimer }
            )
        }
    }) { _, _ ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(AppTheme.dimensions.medium)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(AppTheme.shapes.medium)
                    .background(AppTheme.colors.surfaces)
                    .padding(16.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${stringResource(id = R.string.verification_code_message)}\n${state().phoneNumber}",
                    color = AppTheme.colors.gray1,
                    style = AppTheme.typography.text,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(16.dp))
                VerificationCodeInput(
                    modifier = Modifier.fillMaxWidth(),
                    code = state()::code,
                    onCode = editCode
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