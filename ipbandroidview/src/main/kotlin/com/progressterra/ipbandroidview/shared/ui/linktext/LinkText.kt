package com.progressterra.ipbandroidview.shared.ui.linktext

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import com.progressterra.ipbandroidview.shared.theme.IpbTheme


@Composable
fun LinkText(
    modifier: Modifier = Modifier, linkTextData: List<LinkTextData>, useComponent: UseLinkText
) {
    val annotatedString = createAnnotatedString(linkTextData)

    ClickableText(
        modifier = modifier,
        text = annotatedString,
        style = IpbTheme.typography.footnoteRegular.copy(textAlign = TextAlign.Center),
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
                withStyle(
                    style = SpanStyle(
                        color = IpbTheme.colors.textDisabled.asColor(),
                        textDecoration = TextDecoration.Underline,
                    ),
                ) { append(linkTextData.text) }
                pop()
            } else append(linkTextData.text)
        }
    }
}