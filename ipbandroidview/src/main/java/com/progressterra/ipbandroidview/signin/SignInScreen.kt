package com.progressterra.ipbandroidview.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.*
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.*

@Composable
fun SignInScreen() {
    Scaffold(topBar = {
        TopAppBarWithBackNav(title = stringResource(id = R.string.authorization), onBack = {})
    }) {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.appColors.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = MaterialTheme.dimensions.small,
                        top = MaterialTheme.dimensions.small,
                        end = MaterialTheme.dimensions.small
                    ),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(MaterialTheme.dimensions.normal))
                            .background(MaterialTheme.appColors.surfaces)
                            .padding(MaterialTheme.dimensions.normal)
                    ) {
                        ThemedTextField(
                            text = "",
                            hint = stringResource(id = R.string.phone_number),
                            onChange = {},
                            enabled = true,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
                        )
                    }
                    LinkText(
                        linkTextData = listOf(
                            LinkTextData(text = stringResource(id = R.string.auth_warning_0)),
                            LinkTextData(
                                text = stringResource(id = R.string.offer),
                                tag = "offer",
                                annotation = "https://stackoverflow.com/questions/65567412/jetpack-compose-text-hyperlink-some-section-of-the-text",
                                onClick = { }),
                            LinkTextData(text = stringResource(id = R.string.and)),
                            LinkTextData(
                                text = stringResource(id = R.string.privacy_policy),
                                tag = "privacy policy",
                                annotation = "https://stackoverflow.com/questions/65567412/jetpack-compose-text-hyperlink-some-section-of-the-text",
                                onClick = {})
                        ),
                        modifier = Modifier.padding(top = MaterialTheme.dimensions.small)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                topStart = MaterialTheme.dimensions.medium,
                                topEnd = MaterialTheme.dimensions.medium
                            )
                        )
                        .background(MaterialTheme.appColors.surfaces)
                        .padding(MaterialTheme.dimensions.small)
                ) {
                    ThemedButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { },
                        text = stringResource(id = R.string.auth_button)
                    )
                    ThemedTextButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = MaterialTheme.dimensions.small),
                        onClick = { },
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
        SignInScreen()
    }
}
