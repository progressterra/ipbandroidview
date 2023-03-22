package com.progressterra.ipbandroidview.pages.proshkawelcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.AuthOrSkipState
import com.progressterra.ipbandroidview.features.AuthOrSkipWelcome
import com.progressterra.ipbandroidview.features.UseAuthOrSkip
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout

@Immutable
data class ProshkaWelcomeState(
    val authOrSkipState: AuthOrSkipState = AuthOrSkipState()
)

interface ProshkaWelcomeInteractor : UseAuthOrSkip

@Composable
fun ProshkaWelcome(
    state: ProshkaWelcomeState,
    interactor: ProshkaWelcomeInteractor
) {
    ThemedLayout { _, _ ->
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier,
                painter = painterResource(id = R.drawable.splash_logo),
                contentDescription = null
            )
            AuthOrSkipWelcome(
                state = state.authOrSkipState,
                useAuthOrSkip = interactor
            )
        }
    }
}