package com.progressterra.ipbandroidview.pages.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.TextButton


@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    state: WelcomeScreenState, useComponent: UseWelcomeScreen
) {
    ThemedLayout(
        modifier = modifier,
        bottomBar = {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    state = state.auth,
                    title = stringResource(R.string.auth_button),
                    useComponent = useComponent
                )
                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    state = state.skip,
                    title = stringResource(R.string.skip_yet),
                    useComponent = useComponent
                )
            }
        }) { _, _ ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                painter = painterResource(id = R.drawable.welcome),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
private fun WelcomeScreenPreview() {
    IpbTheme {
        WelcomeScreen(state = WelcomeScreenState(), useComponent = UseWelcomeScreen.Empty())
    }
}
