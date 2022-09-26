package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.theme.AppTheme

private val roundingCornerSize = 8.dp
private const val digitsCount = 4
private val spaceBetweenDigits = 12.dp
private val boxWidth = 56.dp

@Composable
fun VerificationCodeInput(
    modifier: Modifier = Modifier,
    code: String,
    onCode: (String) -> Unit,
) {
    var isFocused by remember { mutableStateOf(false) }
    BasicTextField(
        modifier = modifier.onFocusChanged {
            isFocused = it.isFocused
        },
        value = code,
        onValueChange = { onCode(it) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(digitsCount) { index ->
                    Digit(
                        if (index >= code.length) "" else code[index].toString(),
                        (code.length == index) && isFocused
                    )
                    if (index != digitsCount - 1) Spacer(modifier = Modifier.size(spaceBetweenDigits))
                }
            }
        })
}


@Composable
private fun Digit(
    digit: String, isActive: Boolean
) {
    val baseModifier = Modifier
        .width(boxWidth)
        .background(
            color = AppTheme.colors.background, shape = RoundedCornerShape(roundingCornerSize)
        )
    val modifier = if (isActive) baseModifier.then(
        Modifier.border(
            width = 1.dp,
            color = AppTheme.colors.primary,
            shape = RoundedCornerShape(roundingCornerSize)
        )
    ) else baseModifier
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = modifier,
            text = digit,
            color = AppTheme.colors.black,
            style = AppTheme.typography.headLine,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun VerificationCodeInputPreview0() {
    AppTheme {
        VerificationCodeInput(code = "124", onCode = {})
    }
}

@Preview
@Composable
fun VerificationCodeInputPreview1() {
    AppTheme {
        VerificationCodeInput(code = "12", onCode = {})
    }
}

@Preview
@Composable
fun VerificationCodeInputPreview2() {
    AppTheme {
        VerificationCodeInput(code = "1245", onCode = {})
    }
}
