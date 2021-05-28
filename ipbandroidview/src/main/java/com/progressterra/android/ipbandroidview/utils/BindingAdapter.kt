package com.progressterra.android.ipbandroidview.utils

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.progressterra.android.ipbandroidview.bonuses_details.tabs.ColorsPalette
import com.progressterra.ipbandroidview.R

@BindingAdapter("app:visibility")
internal fun visibility(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("app:textFromInt")
internal fun textFromInt(view: TextView, text: Int) {
    view.text = text.toString()
}

@BindingAdapter("app:setColorAndTextFormattingByQuantity")
internal fun setColorAndTextFormattingByQuantity(textView: TextView, quantity: Int) {
    if (quantity <= 0) {
        textView.setTextColor(ColorsPalette.negativeTextColor ?: Color.RED)
        textView.text = String.format(
            textView.context.getString(R.string.negative_sum), kotlin.math.abs(quantity)
        )
    } else {
        textView.setTextColor(ColorsPalette.positiveTextColor ?: Color.RED)
        textView.text = String.format(
            textView.context.getString(R.string.positive_sum), kotlin.math.abs(quantity)
                .toString()
        )
    }
}

@BindingAdapter("app:setVisibilityMainContentByScreenState")
internal fun setVisibilityMainContentByScreenState(mainContent: View, screenState: ScreenState) {
    when (screenState) {
        ScreenState.DEFAULT -> mainContent.visibility = View.VISIBLE
        ScreenState.LOADING -> mainContent.visibility = View.GONE
        ScreenState.ERROR -> mainContent.visibility = View.GONE
    }
}

@BindingAdapter("app:setVisibilityLoaderByScreenState")
internal fun setVisibilityLoaderByScreenState(loader: View, screenState: ScreenState) {
    when (screenState) {
        ScreenState.DEFAULT -> loader.visibility = View.GONE
        ScreenState.LOADING -> loader.visibility = View.VISIBLE
        ScreenState.ERROR -> loader.visibility = View.GONE
    }
}

@BindingAdapter("app:setVisibilityRefreshContentScreenState")
internal fun setVisibilityRefreshContentScreenState(refreshButton: View, screenState: ScreenState) {
    when (screenState) {
        ScreenState.DEFAULT -> refreshButton.visibility = View.GONE
        ScreenState.LOADING -> refreshButton.visibility = View.GONE
        ScreenState.ERROR -> refreshButton.visibility = View.VISIBLE
    }
}

@BindingAdapter("app:setBonusTransactionType")
internal fun setBonusTransactionType(textView: TextView, type: Int) {
    when (type) {
        0 -> textView.text = textView.context.getText(R.string.type_operation_charging)
        1 -> textView.text = textView.context.getText(R.string.type_operation_spending)
        2 -> textView.text = textView.context.getText(R.string.type_operation_charging)
    }
}


@BindingAdapter("app:setMainTextColor")
internal fun setMainTextColor(textView: TextView, colorsPalette: ColorsPalette?) {
    val color = ColorsPalette.mainTextColor
    color?.let {
        textView.setTextColor(color)
    }
}

@BindingAdapter("app:setSecondaryTextColor")
internal fun setSecondaryTextColor(textView: TextView, colorsPalette: ColorsPalette) {
    val color = ColorsPalette.secondaryTextColor
    color?.let {
        textView.setTextColor(color)
    }
}

@BindingAdapter("app:setMainColor")
internal fun setMainColor(View: View, colorsPalette: ColorsPalette?) {
    val color = ColorsPalette.mainColor
    color?.let {
        View.setBackgroundColor(color)
    }
}

@BindingAdapter("app:setSecondaryColor")
internal fun setSecondaryColor(view: View, colorsPalette: ColorsPalette) {
    val color = ColorsPalette.secondaryColor
    color?.let {
        view.setBackgroundColor(color)
    }
}




