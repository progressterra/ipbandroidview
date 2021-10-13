package com.progressterra.ipbandroidview.utils.ui.binding

import android.view.View
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
