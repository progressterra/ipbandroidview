package com.progressterra.ipbandroidview.utils.ui.binding

import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import com.progressterra.ipbandroidview.ui.bonuses_details.tabs.ColorsPalette
import com.progressterra.ipbandroidview.utils.ScreenState

@BindingConversion
fun convertBooleanToVisibility(isVisible: Boolean): Int = if (isVisible) View.VISIBLE else View.GONE


@BindingAdapter("textFromInt")
internal fun textFromInt(view: TextView, text: Int) {
    view.text = text.toString()
}


@BindingAdapter("setVisibilityMainContentByScreenState")
internal fun setVisibilityMainContentByScreenState(mainContent: View, screenState: ScreenState) {
    when (screenState) {
        ScreenState.DEFAULT -> mainContent.visibility = View.VISIBLE
        ScreenState.LOADING -> mainContent.visibility = View.GONE
        ScreenState.ERROR -> mainContent.visibility = View.GONE
    }
}

@BindingAdapter("setVisibilityLoaderByScreenState")
internal fun setVisibilityLoaderByScreenState(loader: View, screenState: ScreenState) {
    when (screenState) {
        ScreenState.DEFAULT -> loader.visibility = View.GONE
        ScreenState.LOADING -> loader.visibility = View.VISIBLE
        ScreenState.ERROR -> loader.visibility = View.GONE
    }
}

@BindingAdapter("setVisibilityRefreshContentScreenState")
internal fun setVisibilityRefreshContentScreenState(refreshButton: View, screenState: ScreenState) {
    when (screenState) {
        ScreenState.DEFAULT -> refreshButton.visibility = View.GONE
        ScreenState.LOADING -> refreshButton.visibility = View.GONE
        ScreenState.ERROR -> refreshButton.visibility = View.VISIBLE
    }
}


@BindingAdapter("setMainTextColor")
internal fun setMainTextColor(textView: TextView, colorsPalette: ColorsPalette?) {
    val color = ColorsPalette.mainTextColor
    color?.let {
        textView.setTextColor(color)
    }
}

@BindingAdapter("setSecondaryTextColor")
internal fun setSecondaryTextColor(textView: TextView, colorsPalette: ColorsPalette?) {
    val color = ColorsPalette.secondaryTextColor
    color?.let {
        textView.setTextColor(color)
    }
}

@BindingAdapter("setMainColor")
internal fun setMainColor(view: View, colorsPalette: ColorsPalette?) {
    val color = ColorsPalette.mainColor
    color?.let {
        view.setBackgroundColor(color)
    }
}

@BindingAdapter("setSecondaryColor")
internal fun setSecondaryColor(view: View, colorsPalette: ColorsPalette?) {
    val color = ColorsPalette.secondaryColor
    color?.let {
        view.setBackgroundColor(color)
    }
}

@BindingAdapter("setDrawableFromPalette")
internal fun setDrawableFromPalette(view: ImageView, drawableRes: Int?) {
    drawableRes?.let {
        view.setImageResource(it)
    }
}

@BindingAdapter("setTintFromPalette")
internal fun setTintFromPalette(view: View, colorsPalette: ColorsPalette?) {
    ColorsPalette.secondaryColor?.let {
        view.backgroundTintList = ColorStateList.valueOf(it)
    }
}
