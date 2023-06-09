package com.progressterra.ipbandroidview.features.chatinput

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField

@Composable
fun ChatInput(
    modifier: Modifier = Modifier,
    state: ChatInputState,
    useComponent: UseChatInput
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(IpbTheme.colors.surface.asBrush())
            .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
        TextField(
            modifier.fillMaxWidth(),
            state = state.input,
            useComponent = useComponent,
            hint = stringResource(R.string.request),
        )
    }
}

@Composable
@Preview
private fun ChatInputPreview() {
    ChatInput(
        state = ChatInputState(),
        useComponent = UseChatInput.Empty()
    )
}
