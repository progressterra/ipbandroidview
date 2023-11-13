package com.progressterra.ipbandroidview.pages.workwatch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button

@Composable
fun WorkWatchScreen(
    modifier: Modifier = Modifier,
    state: WorkWatchScreenState,
    useComponent: UseWorkWatchScreen
) {
    ThemedLayout(modifier = modifier) { _, _ ->
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly) {
            Button(
                state = state.ask,
                title = stringResource(id = R.string.ask_permission),
                useComponent = useComponent
            )
            Button(
                state = state.enable,
                title = stringResource(id = R.string.enable),
                useComponent = useComponent
            )
        }
    }
}
