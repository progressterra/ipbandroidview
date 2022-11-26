package com.progressterra.ipbandroidview.composable.component

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
import com.progressterra.ipbandroidview.composable.element.ThemedSwitch
import com.progressterra.ipbandroidview.composable.element.ThemedTextField
import com.progressterra.ipbandroidview.theme.AppTheme

@Immutable
interface ReceiveReceiptState {

    val receiveReceipt: Boolean

    val email: String
}

@Composable
fun ReceiveReceipt(
    modifier: Modifier = Modifier,
    state: () -> ReceiveReceiptState,
    check: (Boolean) -> Unit,
    email: (String) -> Unit
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
            ThemedSwitch(onChange = check, checked = state()::receiveReceipt)
        }
        if (state().receiveReceipt)
            ThemedTextField(
                modifier = Modifier.fillMaxWidth(),
                text = state()::email,
                hint = stringResource(R.string.email),
                onChange = email
            )
    }
}