package com.progressterra.ipbandroidview.composable

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.ui.TextField
import com.progressterra.ipbandroidview.shared.ui.TextFieldState
import com.progressterra.ipbandroidview.shared.ui.UseTextField
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Immutable
data class ReceiveReceiptComponentState(
    val receiveReceipt: Boolean = false,
    val emailState: TextFieldState = TextFieldState()
)

interface UseReceiveReceiptComponent : UseTextField {

    fun handleEvent(id: String, event: ReceiveReceiptComponentEvent)
}

sealed class ReceiveReceiptComponentEvent {
    data class ReceiveReceiptChanged(val receiveReceipt: Boolean) : ReceiveReceiptComponentEvent()
}

/**
 * email - text field
 */
@Composable
fun ReceiveReceiptComponent(
    modifier: Modifier = Modifier,
    id: String,
    state: ReceiveReceiptComponentState,
    useComponent: UseReceiveReceiptComponent
) {
    Column(
        modifier = modifier
            .clip(IpbTheme.shapes.medium)
            .background(IpbTheme.colors.surfaces)
            .animateContentSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.receive_check),
                style = IpbTheme.typography.title,
                color = IpbTheme.colors.black
            )
            ThemedSwitch(onChange = {
                useComponent.handleEvent(
                    id, ReceiveReceiptComponentEvent.ReceiveReceiptChanged(it)
                )
            }, checked = state.receiveReceipt)
        }
        if (state.receiveReceipt) TextField(
            modifier = Modifier.fillMaxWidth(),
            id = "email",
            state = state.emailState,
            useComponent = useComponent
        )
    }
}