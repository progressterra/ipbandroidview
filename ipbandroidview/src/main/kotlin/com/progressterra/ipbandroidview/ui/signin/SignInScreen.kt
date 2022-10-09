package com.progressterra.ipbandroidview.ui.signin

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.*
import com.progressterra.ipbandroidview.core.ConfigureScreen
import com.progressterra.ipbandroidview.core.ScreenConfiguration
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun SignInScreen(state: SignInState, interactor: SignInInteractor, settings: SignInSettings, configureScreen: ConfigureScreen) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val title = stringResource(id = R.string.authorization)
    LaunchedEffect(lifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
        configureScreen.configureScreen(
            ScreenConfiguration(title = title, topBarVisibility = true)
        )
    }
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
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
                    )
                }
                //TODO url opener
                LinkText(
                    linkTextData = listOf(
                        LinkTextData(text = stringResource(id = R.string.auth_warning_0)),
                        LinkTextData(text = stringResource(id = R.string.offer),
                            tag = "offer",
                            annotation = stringResource(id = R.string.offer_url),
                            onClick = { Log.d("CLICK", "SignInScreen: $it") }),
                        LinkTextData(text = stringResource(id = R.string.and)),
                        LinkTextData(text = stringResource(id = R.string.privacy_policy),
                            tag = "privacy policy",
                            annotation = stringResource(id = R.string.privacy_policy_url),
                            onClick = { Log.d("CLICK", "SignInScreen: $it") })
                    ), modifier = Modifier.padding(top = AppTheme.dimensions.small)
                )
            }
            BottomHolder {
                ThemedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { interactor.onNext() },
                    text = stringResource(id = R.string.auth_button)
                )
                if (settings.type == SignInScreenType.PASSABLE) {
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
private fun SplashScreenPreview() {
    AppTheme {
        SignInScreen(
            SignInState("+7 (999) 999-99-99", true),
            SignInInteractor.Empty(),
            SignInSettings(SignInScreenType.PASSABLE)
        , ConfigureScreen.Empty())
    }
}

@Preview
@Composable
private fun SplashScreenPreviewWithOnlyAuth() {
    AppTheme {
        SignInScreen(
            SignInState("+7 (999) 999-99-99", true),
            SignInInteractor.Empty(),
            SignInSettings(SignInScreenType.REQUIRING_AUTH)
        , ConfigureScreen.Empty())
    }
}

