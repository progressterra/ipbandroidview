package com.progressterra.ipbandroidview.shared.ui.linktext

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle

@OptIn(ExperimentalTextApi::class)
@Composable
fun LinkText(
    modifier: Modifier = Modifier,
    linkTextData: List<LinkTextData>,
    style: TextStyle,
    brush: Brush,
    textAlign: TextAlign = TextAlign.Center,
    useComponent: UseLinkText
) {
    val annotatedString = createAnnotatedString(linkTextData)

    ClickableText(
        modifier = modifier,
        text = annotatedString,
        style = style.copy(textAlign = textAlign, brush = brush),
        onClick = { offset ->
            linkTextData.forEach { annotatedStringData ->
                annotatedStringData.url?.let { url ->
                    annotatedString.getStringAnnotations(
                        tag = annotatedStringData.url,
                        start = offset,
                        end = offset,
                    ).firstOrNull()?.let {
                        useComponent.handle(LinkTextEvent.Click(url))
                    }
                }
            }
        }
    )
}

@Composable
private fun createAnnotatedString(data: List<LinkTextData>): AnnotatedString {
    return buildAnnotatedString {
        data.forEach { linkTextData ->
            if (linkTextData.url != null) {
                pushStringAnnotation(tag = linkTextData.url, annotation = linkTextData.url)
                withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                    append(
                        linkTextData.text
                    )
                }
                pop()
            } else append(linkTextData.text)
        }
    }
}