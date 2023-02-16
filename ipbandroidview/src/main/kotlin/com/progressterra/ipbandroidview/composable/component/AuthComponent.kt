package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.BottomHolder
import com.progressterra.ipbandroidview.composable.LinkText
import com.progressterra.ipbandroidview.composable.LinkTextData
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

data class AuthComponentState(
    val phoneNumber: String = "", val isDataValid: Boolean = false
)

interface AuthComponentInteractor {

    fun onNext()

    fun onSkip()

    fun editPhoneNumber(phoneNumber: String)

    fun onUrlClick(url: String)

    class Empty : AuthComponentInteractor {

        override fun onNext() = Unit

        override fun onSkip() = Unit

        override fun editPhoneNumber(phoneNumber: String) = Unit

        override fun onUrlClick(url: String) = Unit
    }
}

/**
 * @param modifier - modifier for the component
 * @param state - state of the component
 * @param interactor - interactor of the component
 * @param prefix - prefix for the phone number
 * @param canBeSkipped - if true, then the skip button will be shown
 * @param offerUrl - url for the offer link
 * @param policyUrl - url for the policy link
 */
@Composable
fun AuthComponent(
    modifier: Modifier = Modifier,
    state: AuthComponentState,
    interactor: AuthComponentInteractor,
    prefix: String,
    canBeSkipped: Boolean,
    offerUrl: String,
    policyUrl: String
) {
    ThemedLayout(modifier = modifier, topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.authorization))
    }, bottomBar = {
        BottomHolder {
            ButtonComponent(
                modifier = Modifier.fillMaxWidth(),
                onClick = { interactor.onNext() },
                text = stringResource(id = R.string.auth_button),
                enabled = state.isDataValid
            )
            if (canBeSkipped) {
                Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
                TextButtonComponent(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { interactor.onSkip() },
                    text = stringResource(id = R.string.auth_skip)
                )
            }
        }
    }) { _, _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.dimensions.small)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(AppTheme.shapes.medium)
                        .background(AppTheme.colors.surfaces)
                        .padding(AppTheme.dimensions.medium)
                ) {
                    TextFieldComponent(
                        modifier = Modifier.fillMaxWidth(),
                        text = state.phoneNumber,
                        hint = stringResource(id = R.string.phone_number),
                        onChange = { interactor.editPhoneNumber(it) },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                        prefix = prefix
                    )
                }
                LinkText(
                    linkTextData = listOf(
                        LinkTextData(text = stringResource(id = R.string.auth_warning_0)),
                        LinkTextData(text = stringResource(id = R.string.offer),
                            tag = "offer",
                            annotation = offerUrl,
                            onClick = { interactor.onUrlClick(it) }),
                        LinkTextData(text = stringResource(id = R.string.and)),
                        LinkTextData(text = stringResource(id = R.string.privacy_policy),
                            tag = "privacy policy",
                            annotation = policyUrl,
                            onClick = { interactor.onUrlClick(it) })
                    ), modifier = Modifier.padding(top = AppTheme.dimensions.small)
                )
            }
        }
    }
}

@Composable
@Preview
private fun AuthComponentPreview() {
    AppTheme {
        AuthComponent(
            state = AuthComponentState(),
            interactor = AuthComponentInteractor.Empty(),
            canBeSkipped = true,
            offerUrl = "https://www.google.com",
            policyUrl = "https://www.google.com",
            prefix = "+"
        )
    }
}