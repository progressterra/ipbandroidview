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
import com.progressterra.ipbandroidview.composable.component.TextField
import com.progressterra.ipbandroidview.composable.component.TextFieldState
import com.progressterra.ipbandroidview.composable.component.UseTextField
import com.progressterra.ipbandroidview.theme.AppTheme

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
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .animateContentSize()
            .padding(AppTheme.dimensions.medium),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.receive_check),
                style = AppTheme.typography.title,
                color = AppTheme.colors.black
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