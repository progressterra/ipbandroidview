package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import com.progressterra.ipbandroidview.theme.AppTheme

data class LinkTextData(
    val text: String,
    val tag: String? = null,
    val annotation: String? = null,
    val onClick: ((String) -> Unit)? = null,
)

@Composable
fun LinkText(
    linkTextData: List<LinkTextData>,
    modifier: Modifier = Modifier,
    style: TextStyle = AppTheme.typography.tertiaryText,
    align: TextAlign = TextAlign.Center,
    color: Color = AppTheme.colors.gray2
) {
    val annotatedString = createAnnotatedString(linkTextData)

    ClickableText(
        text = annotatedString,
        style = style.copy(
            textAlign = align, color = color
        ),
        onClick = { offset ->
            linkTextData.forEach { annotatedStringData ->
                if (annotatedStringData.tag != null && annotatedStringData.annotation != null) {
                    annotatedString.getStringAnnotations(
                        tag = annotatedStringData.tag,
                        start = offset,
                        end = offset,
                    ).firstOrNull()?.let {
                        annotatedStringData.onClick?.invoke(it.item)
                    }
                }
            }
        },
        modifier = modifier,
    )
}

@Composable
private fun createAnnotatedString(data: List<LinkTextData>): AnnotatedString {
    return buildAnnotatedString {
        data.forEach { linkTextData ->
            if (linkTextData.tag != null && linkTextData.annotation != null) {
                pushStringAnnotation(
                    tag = linkTextData.tag,
                    annotation = linkTextData.annotation,
                )
                withStyle(
                    style = SpanStyle(
                        color = AppTheme.colors.primary,
                        textDecoration = TextDecoration.Underline,
                    ),
                ) {
                    append(linkTextData.text)
                }
                pop()
            } else {
                append(linkTextData.text)
            }
        }
    }
}