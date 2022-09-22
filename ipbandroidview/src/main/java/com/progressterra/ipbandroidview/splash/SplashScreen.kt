package com.progressterra.ipbandroidview.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.AppTheme
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.appColors

@Composable
fun SplashScreen() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.appColors.surfaces) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp),
                painter = painterResource(id = R.drawable.splash_logo),
                contentDescription = stringResource(id = R.string.splash_logo)
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    AppTheme {
        SplashScreen()
    }
}