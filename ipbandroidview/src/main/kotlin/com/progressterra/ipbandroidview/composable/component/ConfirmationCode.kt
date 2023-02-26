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
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.VerificationCodeInput
import com.progressterra.ipbandroidview.theme.AppTheme

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
            Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
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
                    editCode = {
                        useComponent.handleEvent(
                            id, ConfirmationCodeEvent.CodeChanged(it)
                        )
                    })
            }
        }
    }
}