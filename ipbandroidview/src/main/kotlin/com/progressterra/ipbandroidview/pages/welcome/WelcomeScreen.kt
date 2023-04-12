package com.progressterra.ipbandroidview.pages.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.authorskip.AuthOrSkipWelcome
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout

@Composable
fun WelcomeScreen(
    state: WelcomeState,
    useComponent: UseWelcome
) {
    ThemedLayout { _, _ ->
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier,
                painter = painterResource(id = R.drawable.proshka_welcome),
                contentDescription = null
            )
            AuthOrSkipWelcome(
                state = state.authOrSkipState,
                useAuthOrSkip = useComponent
            )
        }
    }
}