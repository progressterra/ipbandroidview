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
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.BottomHolder
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.VerificationCodeInput
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.theme.AppTheme

data class ConfirmationCodeComponentState(
    val code: String = "",
    val phoneNumber: String = "",
    val timer: String = "",
    val canResend: Boolean = false,
    val screenState: ScreenState = ScreenState.LOADING
)

interface ConfirmationCodeComponentInteractor {

    fun onBack()

    fun onNext()

    fun resend()

    fun codeChanged(code: String)

    class Empty : ConfirmationCodeComponentInteractor {

        override fun onBack() = Unit

        override fun onNext() = Unit

        override fun resend() = Unit

        override fun codeChanged(code: String) = Unit
    }
}

/**
 * @param modifier - modifier for the component
 * @param state - state of the component
 * @param interactor - interactor of the component
 */
@Composable
fun ConfirmationCodeComponent(
    modifier: Modifier = Modifier,
    state: ConfirmationCodeComponentState,
    interactor: ConfirmationCodeComponentInteractor
) {
    ThemedLayout(modifier = modifier, topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.verification_code),
            onBack = { interactor.onBack() })
    }, bottomBar = {
        BottomHolder {
            ButtonComponent(
                modifier = Modifier.fillMaxWidth(),
                onClick = { interactor.onNext() },
                text = stringResource(id = R.string.next)
            )
            Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
            ThemedTextButton(modifier = Modifier.fillMaxWidth(),
                onClick = { interactor.resend() },
                text = state.timer.ifBlank { stringResource(id = R.string.resend) },
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
                VerificationCodeInput(modifier = Modifier.fillMaxWidth(),
                    code = state.code,
                    editCode = { interactor.codeChanged(it) })
            }
        }
    }
}

@Preview
@Composable
private fun ConfirmationCodeComponentPreview() {
    AppTheme {
        ConfirmationCodeComponent(
            state = ConfirmationCodeComponentState(),
            interactor = ConfirmationCodeComponentInteractor.Empty()
        )
    }
}