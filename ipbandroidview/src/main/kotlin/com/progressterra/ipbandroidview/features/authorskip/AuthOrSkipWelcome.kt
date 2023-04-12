package com.progressterra.ipbandroidview.features.authorskip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.ButtonStyle

@Composable
fun AuthOrSkipWelcome(
    modifier: Modifier = Modifier,
    state: AuthOrSkipState,
    useAuthOrSkip: UseAuthOrSkip
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            state = state.auth,
            title = stringResource(R.string.auth_button),
            useComponent = useAuthOrSkip
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            state = state.skip,
            style = ButtonStyle.SILENT,
            title = stringResource(R.string.auth_skip_button),
            useComponent = useAuthOrSkip
        )
    }
}