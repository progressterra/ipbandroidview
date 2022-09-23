package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.AppTheme

private val roundingCornerSize = 8.dp
private const val digitsCount = 4
private val spaceBetweenDigits = 12.dp
private val boxWidth = 56.dp

//TODO Active border

@Composable
fun VerificationCodeInput(
    modifier: Modifier = Modifier,
    pinText: String,
    onPinTextChange: (String) -> Unit,
) {
    BasicTextField(modifier = modifier,
        value = pinText,
        onValueChange = onPinTextChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(digitsCount) { index ->
                    Digit(index, pinText)
                    if (index != digitsCount - 1) Spacer(modifier = Modifier.size(spaceBetweenDigits))
                }
            }
        })
}


@Composable
private fun Digit(
    index: Int,
    pinText: String,
) {
    val modifier = Modifier
        .width(boxWidth)
        .background(
            color = AppTheme.colors.background, shape = RoundedCornerShape(roundingCornerSize)
        )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (index >= pinText.length) "" else pinText[index].toString(),
            color = AppTheme.colors.black,
            modifier = modifier,
            style = AppTheme.typography.headLine,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun VerificationCodeInputPreview() {
    AppTheme {
        VerificationCodeInput(pinText = "1245", onPinTextChange = {})
    }
}
