package com.progressterra.ipbandroidview.utils.ui.binding

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.progressterra.ipbandroidview.ui.login.login.ScreenState

@BindingAdapter("visibility")
fun visibility(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("tintForDigitItem")
fun setTintForDigitItem(textView: TextView, string: String) {
    if (textView.text.isNullOrEmpty()) {
        textView.backgroundTintList = ColorStateList.valueOf(Color.RED)
    } else {
        textView.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
    }
}

@BindingAdapter("showLoadingScreenComponent")
fun showLoadingScreenComponent(view: View, screenState: ScreenState) {
    when (screenState) {
        ScreenState.LOADING -> view.visibility = View.VISIBLE
        else -> view.visibility = View.GONE
    }
}

@BindingAdapter("showBaseScreenComponent")
fun showBaseScreenComponent(view: View, screenState: ScreenState) {
    when (screenState) {
        ScreenState.DEFAULT -> view.visibility = View.VISIBLE
        else -> view.visibility = View.INVISIBLE
    }
}
