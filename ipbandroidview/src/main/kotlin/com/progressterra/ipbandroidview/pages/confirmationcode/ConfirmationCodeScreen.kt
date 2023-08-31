package com.progressterra.ipbandroidview.pages.confirmationcode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.code.Code
import com.progressterra.ipbandroidview.features.code.CodeState
import com.progressterra.ipbandroidview.features.countdown.CountDown
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout

@Composable
fun ConfirmationCodeScreen(
    modifier: Modifier = Modifier,
    state: ConfirmationCodeScreenState,
    useComponent: UseConfirmationCodeScreen
) {
    ThemedLayout(
        modifier = modifier,
        topBar = {
            TopBar(
                title = stringResource(R.string.verification_code),
                useComponent = useComponent
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(IpbTheme.colors.surface.asBrush())
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CountDown(
                    modifier = Modifier.fillMaxWidth(),
                    state = state.repeat,
                    useComponent = useComponent
                )
            }
        }
    ) { _, _ ->
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Code(
                modifier = Modifier.padding(horizontal = 8.dp),
                state = state.code,
                useComponent = useComponent
            )
        }
    }
}

@Preview
@Composable
private fun ConfirmationCodeScreenPreview() {
    IpbTheme {
        ConfirmationCodeScreen(
            state = ConfirmationCodeScreenState(
                code = CodeState(code = "123", phone = "123456789")
            ), useComponent = UseConfirmationCodeScreen.Empty()
        )
    }
}