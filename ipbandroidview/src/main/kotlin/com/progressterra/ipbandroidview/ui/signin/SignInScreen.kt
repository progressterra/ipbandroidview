package com.progressterra.ipbandroidview.ui.signin

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
import com.progressterra.ipbandroidview.composable.ThemedButton
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTextButton
import com.progressterra.ipbandroidview.composable.ThemedTextField
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun SignInScreen(
    state: SignInState,
    interactor: SignInInteractor,
    settings: SignInSettings
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.authorization))
    }, bottomBar = {
        BottomHolder {
            ThemedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { interactor.onNext() },
                text = stringResource(id = R.string.auth_button)
            )
            if (settings.passable) {
                Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
                ThemedTextButton(
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
                    ThemedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        text = "+${state.phoneNumber}",
                        hint = stringResource(id = R.string.phone_number),
                        onChange = { interactor.editPhoneNumber(it) },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
                    )
                }
                LinkText(
                    linkTextData = listOf(
                        LinkTextData(text = stringResource(id = R.string.auth_warning_0)),
                        LinkTextData(
                            text = stringResource(id = R.string.offer),
                            tag = "offer",
                            annotation = stringResource(id = R.string.offer_url),
                            onClick = { interactor.openUrl(it) }
                        ),
                        LinkTextData(text = stringResource(id = R.string.and)),
                        LinkTextData(
                            text = stringResource(id = R.string.privacy_policy),
                            tag = "privacy policy",
                            annotation = stringResource(id = R.string.privacy_policy_url),
                            onClick = { interactor.openUrl(it) }
                        )
                    ),
                    modifier = Modifier.padding(top = AppTheme.dimensions.small)
                )
            }
        }
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    AppTheme {
    }
}

