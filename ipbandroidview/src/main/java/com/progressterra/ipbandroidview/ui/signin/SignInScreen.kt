package com.progressterra.ipbandroidview.ui.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.theme.AppTheme
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.*
import com.progressterra.ipbandroidview.composable.linktext.LinkText
import com.progressterra.ipbandroidview.composable.linktext.LinkTextData

@Composable
fun SignInScreen(state: SignInState, interactor: SignInInteractor) {
    Scaffold(topBar = {
        TopAppBarWithBackNav(title = stringResource(id = R.string.authorization),
            onBack = { interactor.onBack() })
    }) {
        Surface(
            modifier = Modifier.fillMaxSize(), color = AppTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = AppTheme.dimensions.small,
                        top = AppTheme.dimensions.small,
                        end = AppTheme.dimensions.small
                    ),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(AppTheme.dimensions.regular))
                            .background(AppTheme.colors.surfaces)
                            .padding(AppTheme.dimensions.regular)
                    ) {
                        ThemedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            text = state.phoneNumber,
                            hint = stringResource(id = R.string.phone_number),
                            onChange = { interactor.onPhoneNumber(it) },
                            enabled = true,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
                        )
                    }
                    LinkText(
                        linkTextData = listOf(
                            LinkTextData(text = stringResource(id = R.string.auth_warning_0)),
                            LinkTextData(text = stringResource(id = R.string.offer),
                                tag = "offer",
                                annotation = stringResource(id = R.string.offer_url),
                                onClick = { }),
                            LinkTextData(text = stringResource(id = R.string.and)),
                            LinkTextData(text = stringResource(id = R.string.privacy_policy),
                                tag = "privacy policy",
                                annotation = stringResource(id = R.string.privacy_policy_url),
                                onClick = {})
                        ), modifier = Modifier.padding(top = AppTheme.dimensions.small)
                    )
                }
                BottomHolder {
                    ThemedButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { interactor.onNext() },
                        text = stringResource(id = R.string.auth_button)
                    )
                    Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
                    ThemedTextButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { interactor.onSkip() },
                        text = stringResource(id = R.string.auth_skip)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    AppTheme {
        SignInScreen(SignInState("+7 (999) 999-99-99", true), SignInInteractor.Empty())
    }
}
