package com.progressterra.ipbandroidview.pages.confirmationcode

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.code.Code
import com.progressterra.ipbandroidview.features.proshkatopbar.ProshkaTopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.widgets.nextorrepeat.NextOrRepeat

@Composable
fun ConfirmationCodeScreen(
    state: ConfirmationCodeState,
    useComponent: UseConfirmationCode
) {
    ThemedLayout(
        topBar = {
            ProshkaTopBar(
                title = stringResource(R.string.verification_code),
                useComponent = useComponent
            )
        },
        bottomBar = {
            NextOrRepeat(
                state = state.nextOrRepeat,
                useComponent = useComponent
            )
        }
    ) { _, _ ->
        Code(
            modifier = Modifier.padding(horizontal = 8.dp),
            state = state.code,
            useComponent = useComponent
        )
    }
}

@Preview
@Composable
private fun ConfirmationCodeScreenPreview() {
    IpbTheme {
    }
}