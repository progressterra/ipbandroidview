package com.progressterra.ipbandroidview.pages.workwatch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Bottom),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                state = state.ask,
                title = stringResource(id = R.string.ask_permission),
                useComponent = useComponent
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                state = state.enable,
                title = stringResource(id = R.string.enable),
                useComponent = useComponent
            )
        }
    }
}
