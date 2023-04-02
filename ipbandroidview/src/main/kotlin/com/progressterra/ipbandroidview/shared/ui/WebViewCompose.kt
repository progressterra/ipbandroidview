package com.progressterra.ipbandroidview.shared.ui

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebViewCompose(
    modifier: Modifier = Modifier,
    url: String
) {
    AndroidView(
        modifier = modifier,
        factory = {
            val webView = WebView(it)
            webView.webViewClient = WebViewClient()
            webView.loadUrl(url)
            webView
        }
    )
}