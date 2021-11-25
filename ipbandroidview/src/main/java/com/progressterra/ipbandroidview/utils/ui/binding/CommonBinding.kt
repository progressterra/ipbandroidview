package com.progressterra.ipbandroidview.utils.ui.binding

import android.util.Base64
import android.view.View
import android.webkit.WebView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import com.progressterra.ipbandroidview.utils.ScreenState

@BindingConversion
fun convertBooleanToVisibility(isVisible: Boolean): Int = if (isVisible) View.VISIBLE else View.GONE

@BindingAdapter("invisible")
fun setInvisible(v: View, isVisible: Boolean) {
    v.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("textFromInt")
internal fun textFromInt(view: TextView, text: Int) {
    view.text = text.toString()
}

@BindingAdapter("setVisibilityLoaderByScreenState")
internal fun setVisibilityLoaderByScreenState(loader: View, screenState: ScreenState) {
    when (screenState) {
        ScreenState.DEFAULT -> loader.visibility = View.GONE
        ScreenState.LOADING -> loader.visibility = View.VISIBLE
        ScreenState.ERROR -> loader.visibility = View.GONE
    }
}


@BindingAdapter("setLoadStateToButton")
internal fun setLoadStateToButton(button: AppCompatButton, isLoading: Boolean) {
    if (isLoading) {
        button.textScaleX = 0.0f
    } else {
        button.textScaleX = 1f
    }
}

@BindingAdapter("setHtmlData")
fun setHtmlData(webView: WebView, url: String?) {
    url ?: return
    val htmlString = Base64.encodeToString(url.toByteArray(), Base64.NO_PADDING)

    webView.loadData(htmlString, "text/html", "base64")
    webView.settings.javaScriptEnabled = true
    webView.settings.apply {
        loadWithOverviewMode = true
        useWideViewPort = true
    }
}
