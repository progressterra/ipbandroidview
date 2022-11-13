package com.progressterra.ipbandroidview.ui.signin

import android.util.Log
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
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.BottomHolder
import com.progressterra.ipbandroidview.components.LinkText
import com.progressterra.ipbandroidview.components.LinkTextData
import com.progressterra.ipbandroidview.components.ThemedButton
import com.progressterra.ipbandroidview.components.ThemedLayout
import com.progressterra.ipbandroidview.components.ThemedTextButton
import com.progressterra.ipbandroidview.components.ThemedTextField
import com.progressterra.ipbandroidview.components.topbar.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun SignInScreen(
    state: () -> SignInState,
    next: () -> Unit,
    skip: () -> Unit,
    editPhoneNumber: (String) -> Unit,
    settings: SignInSettings,
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(title = { stringResource(id = R.string.authorization) })
    }, bottomBar = {
        BottomHolder {
            ThemedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = next,
                text = { stringResource(id = R.string.auth_button) }
            )
            if (settings.type == SignInScreenType.PASSABLE) {
                Spacer(modifier = Modifier.size(8.dp))
                ThemedTextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = skip,
                    text = { stringResource(id = R.string.auth_skip) }
                )
            }
        }
    }) { _, _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.dimensions.medium)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(AppTheme.shapes.medium)
                        .background(AppTheme.colors.surfaces)
                        .padding(12.dp)
                ) {
                    ThemedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        text = state()::phoneNumber,
                        hint = { stringResource(id = R.string.phone_number) },
                        onChange = { editPhoneNumber(it) },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
                    )
                }
                //TODO url opener
                val list = listOf(
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
                )
                LinkText(
                    linkTextData = { list }, modifier = Modifier.padding(top = 8.dp)
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

