package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.element.BottomHolder
import com.progressterra.ipbandroidview.composable.element.SendIcon
import com.progressterra.ipbandroidview.composable.element.ThemedTextField
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun ChatInput(
    modifier: Modifier = Modifier,
    editMessage: (String) -> Unit,
    message: () -> String,
    onSend: () -> Unit,
    enabled: () -> Boolean
) {
    BottomHolder(modifier = modifier) {
        Text(
            text = stringResource(R.string.support_info),
            color = AppTheme.colors.gray2,
            style = AppTheme.typography.tertiaryText
        )
        Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
        Row(
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ThemedTextField(
                modifier = Modifier.weight(1f),
                text = message,
                hint = stringResource(R.string.request),
                onChange = editMessage,
                action = onSend,
                enabled = enabled
            )
            IconButton(
                enabled = enabled(),
                onClick = onSend
            ) {
                SendIcon()
            }
        }
        Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
    }
}

@Composable
@Preview
private fun ChatInputPreview() {
    AppTheme {
        ChatInput(
            editMessage = {},
            message = { "Some really cool message!!" },
            onSend = {},
            enabled = { true }
        )
    }
}