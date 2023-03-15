package com.progressterra.ipbandroidview.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.IpbTheme

@Composable
fun SplashScreen(state: SplashState, settings: SplashSettings) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(IpbTheme.colors.surfaces),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .width(settings.logoWidth)
                .height(settings.logoHeight),
            painter = painterResource(id = state.logoId),
            contentDescription = stringResource(id = R.string.splash_logo)
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    IpbTheme {
        SplashScreen(
            SplashState(
                R.drawable.splash_logo,
            ), SplashSettings(
                logoHeight = 150.dp, logoWidth = 150.dp
            )
        )
    }
}