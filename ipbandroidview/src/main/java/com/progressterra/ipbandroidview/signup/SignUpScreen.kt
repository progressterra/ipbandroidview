package com.progressterra.ipbandroidview.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.AppTheme
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.ThemedButton
import com.progressterra.ipbandroidview.composable.ThemedTextButton
import com.progressterra.ipbandroidview.composable.ThemedTextField
import com.progressterra.ipbandroidview.composable.TopAppBarWithBackNav

@Composable
fun SignUpScreen() {
    Scaffold(topBar = {
        TopAppBarWithBackNav(title = stringResource(id = R.string.signup), onBack = {})
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(AppTheme.dimensions.normal))
                        .background(AppTheme.colors.surfaces)
                        .padding(AppTheme.dimensions.normal)
                ) {
                    ThemedTextField(text = "", hint = stringResource(id = R.string.name_surname), onChange = {})
                    Spacer(modifier = Modifier.size(AppTheme.dimensions.normal))
                    ThemedTextField(text = "", hint = stringResource(id = R.string.email), onChange = {})
                    Spacer(modifier = Modifier.size(AppTheme.dimensions.normal))
                    ThemedTextField(text = "", hint = stringResource(id = R.string.birthday), onChange = {})
                    Spacer(modifier = Modifier.size(AppTheme.dimensions.normal))
                    ThemedTextField(
                        text = "899999419",
                        hint = stringResource(id = R.string.phone_number),
                        onChange = {},
                        enabled = false
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                topStart = AppTheme.dimensions.medium, topEnd = AppTheme.dimensions.medium
                            )
                        )
                        .background(AppTheme.colors.surfaces)
                        .padding(AppTheme.dimensions.small)
                ) {
                    ThemedButton(
                        modifier = Modifier.fillMaxWidth(), onClick = { }, text = stringResource(id = R.string.next)
                    )
                    Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
                    ThemedTextButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { },
                        text = stringResource(id = R.string.auth_skip)
                    )
                    Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
                }
            }
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    AppTheme {
        SignUpScreen()
    }
}
