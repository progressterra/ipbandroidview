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
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.*
import com.progressterra.ipbandroidview.R

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
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.appColors.surfaces)
                            .padding(12.dp)
                    ) {
                        ThemedTextField(
                            text = "",
                            hint = stringResource(id = R.string.phone_number),
                            onChanged = {},
                            enabled = true,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
                        )
                    }
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
