package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun VerificationCodeInput(
    modifier: Modifier = Modifier,
    code: String = "",
    onCode: ((String) -> Unit)? = null,
    digitsCount: Int = 4,
    roundingCornerSize: Dp = 8.dp,
    spaceBetweenDigits: Dp = 12.dp,
    boxWidth: Dp = 56.dp
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
                    .width(boxWidth)
                    .background(
                        color = AppTheme.colors.background,
                        shape = RoundedCornerShape(roundingCornerSize)
                    )
                    .border(
                        width = 1.dp,
                        color = if (active) AppTheme.colors.primary else Color.Transparent,
                        shape = RoundedCornerShape(roundingCornerSize)
                    ),
                text = digit,
                color = AppTheme.colors.black,
                style = AppTheme.typography.headLine,
                textAlign = TextAlign.Center
            )
        }
    }

    var localCode by remember(code) {
        mutableStateOf(code)
    }
    val mutableInteractionSource = remember { MutableInteractionSource() }
    val focused = mutableInteractionSource.collectIsFocusedAsState().value
    val focusManager = LocalFocusManager.current
    BasicTextField(modifier = modifier.clearFocusOnKeyboardDismiss(),
        value = localCode,
        singleLine = true,
        maxLines = 1,
        interactionSource = mutableInteractionSource,
        onValueChange = {
            if (it.length <= 4) {
                localCode = it
                onCode?.invoke(it)
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
                repeat(digitsCount) { index ->
                    Digit(
                        if (index >= localCode.length) "" else localCode[index].toString(),
                        (localCode.length == index) && focused
                    )
                    if (index != digitsCount - 1) Spacer(modifier = Modifier.size(spaceBetweenDigits))
                }
            }
        })
}

@Preview
@Composable
fun VerificationCodeInputPreview0() {
    AppTheme {
        VerificationCodeInput()
    }
}