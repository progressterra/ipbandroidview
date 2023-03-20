package com.progressterra.ipbandroidview.composable.component

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
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.BottomHolder
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.VerificationCodeInput
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.Button
import com.progressterra.ipbandroidview.shared.ui.ButtonState
import com.progressterra.ipbandroidview.shared.ui.UseButton

data class ConfirmationCodeState(
    val code: String = "",
    val phoneNumber: String = "",
    val nextButton: ButtonState,
    val resendButton: TextButtonState
)

sealed class ConfirmationCodeEvent {

    object Back : ConfirmationCodeEvent()

    data class CodeChanged(val code: String) : ConfirmationCodeEvent()
}

interface UseConfirmationCode : UseButton, UseTextButton {

    fun handleEvent(id: String, event: ConfirmationCodeEvent)
}

/**
 * resend - text button
 * next - button
 */
@Composable
fun ConfirmationCode(
    modifier: Modifier = Modifier,
    id: String,
    state: ConfirmationCodeState,
    useComponent: UseConfirmationCode
) {
    ThemedLayout(modifier = modifier, topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.verification_code),
            onBack = { useComponent.handleEvent(id, ConfirmationCodeEvent.Back) })
    }, bottomBar = {
        BottomHolder {
            Button(
                id = "next",
                modifier = Modifier.fillMaxWidth(),
                state = state.nextButton,
                useComponent = useComponent
            )
            Spacer(modifier = Modifier.size(8.dp))
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                id = "resend",
                state = state.resendButton,
                useComponent = useComponent
            )
        }
    }) { _, _ ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(IpbTheme.shapes.medium)
                    .background(IpbTheme.colors.surfaces)
                    .padding(16.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${stringResource(id = R.string.verification_code_message)}\n${state.phoneNumber}",
                    color = IpbTheme.colors.gray1,
                    style = IpbTheme.typography.text,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(16.dp))
                VerificationCodeInput(
                    modifier = Modifier.fillMaxWidth(),
                    code = state.code,
                    editCode = {
                        useComponent.handleEvent(
                            id, ConfirmationCodeEvent.CodeChanged(it)
                        )
                    })
            }
        }
    }
}