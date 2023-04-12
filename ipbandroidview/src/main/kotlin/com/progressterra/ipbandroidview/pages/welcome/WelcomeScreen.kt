package com.progressterra.ipbandroidview.pages.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(343f / 547f),
                painter = painterResource(id = R.drawable.proshka_welcome),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(80.dp))
            AuthOrSkipWelcome(
                state = state.authOrSkipState,
                useAuthOrSkip = useComponent
            )
        }
    }
}