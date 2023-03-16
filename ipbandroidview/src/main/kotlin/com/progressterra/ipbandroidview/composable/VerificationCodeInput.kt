package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.composable.utils.clearFocusOnKeyboardDismiss
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

private val digitSize = 56.dp

private val borderWidth = 1.dp

@Composable
fun VerificationCodeInput(
    modifier: Modifier = Modifier,
    code: String,
    editCode: (String) -> Unit
) {

    @Composable
    fun Digit(
        digit: String,
        active: Boolean
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .width(digitSize)
                    .background(
                        color = IpbTheme.colors.background,
                        shape = IpbTheme.shapes.small
                    )
                    .border(
                        width = borderWidth,
                        color = if (active) IpbTheme.colors.primary else Color.Transparent,
                        shape = IpbTheme.shapes.small
                    ),
                text = digit,
                color = IpbTheme.colors.black,
                style = IpbTheme.typography.headLine,
                textAlign = TextAlign.Center
            )
        }
    }

    val mutableInteractionSource = remember { MutableInteractionSource() }
    val focused = mutableInteractionSource.collectIsFocusedAsState().value
    val focusManager = LocalFocusManager.current
    BasicTextField(modifier = modifier.clearFocusOnKeyboardDismiss(),
        value = code,
        singleLine = true,
        maxLines = 1,
        interactionSource = mutableInteractionSource,
        onValueChange = {
            if (it.length <= 4) {
                editCode(it)
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
                        if (index >= code.length) "" else code[index].toString(),
                        (code.length == index) && focused
                    )
                    if (index != 4 - 1) Spacer(modifier = Modifier.size(12.dp))
                }
            }
        })
}

@Preview
@Composable
private fun VerificationCodeInputPreview0() {
    IpbTheme {
        VerificationCodeInput(code = "", editCode = {})
    }
}