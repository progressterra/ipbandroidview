package com.progressterra.ipbandroidview.features.code

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.theme.toBrush
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.clearFocusOnKeyboardDismiss

@Composable
private fun Digit(
    digit: String, active: Boolean
) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(IpbTheme.colors.background.asBrush())
            .border(
                width = 1.dp,
                brush = if (active) IpbTheme.colors.primary.asBrush() else Color.Transparent.toBrush(),
                shape = RoundedCornerShape(8.dp)
            ), contentAlignment = Alignment.Center
    ) {
        if (!active && digit.isEmpty()) {
            BrushedText(
                text = "·",
                style = IpbTheme.typography.largeTitle,
                tint = IpbTheme.colors.textDisabled.asBrush()
            )
        }
        BrushedText(
            text = digit,
            style = IpbTheme.typography.largeTitle,
            tint = IpbTheme.colors.textPrimary.asBrush()
        )
    }
}

@Composable
fun Code(
    modifier: Modifier = Modifier, state: CodeState, useComponent: UseCode
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(IpbTheme.colors.surface.asBrush())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BrushedText(
            modifier = Modifier.fillMaxWidth(),
            text = "${stringResource(id = R.string.verification_code_message)}\n${state.phone}",
            tint = IpbTheme.colors.textSecondary.asBrush(),
            style = IpbTheme.typography.body,
            textAlign = TextAlign.Center
        )
        val mutableInteractionSource = remember { MutableInteractionSource() }
        val focused = mutableInteractionSource.collectIsFocusedAsState().value
        val focusManager = LocalFocusManager.current
        var innerValue by remember { mutableStateOf(TextFieldValue()) }
        val focusRequester = remember { FocusRequester() }
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
        LaunchedEffect(focused) {
            if (focused) {
                innerValue = innerValue.copy(selection = TextRange(innerValue.text.length))
            }
        }
        LaunchedEffect(state.code) {
            innerValue = innerValue.copy(text = state.code)
        }
        BasicTextField(
            modifier = Modifier
                .focusRequester(focusRequester)
                .clearFocusOnKeyboardDismiss(),
            value = innerValue,
            singleLine = true,
            maxLines = 1,
            interactionSource = mutableInteractionSource,
            onValueChange = { value ->
                if (value.text.length <= 4) {
                    useComponent.handle(CodeEvent(value.text))
                    innerValue = value
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
            decorationBox = {
                Row(horizontalArrangement = Arrangement.Center) {
                    repeat(4) { index ->
                        Digit(
                            if (index >= state.code.length) "" else state.code[index].toString(),
                            (state.code.length == index) && focused
                        )
                        if (index != 4 - 1) Spacer(modifier = Modifier.size(12.dp))
                    }
                }
            })
    }
}