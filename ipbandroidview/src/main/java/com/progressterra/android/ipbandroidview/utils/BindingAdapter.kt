package com.progressterra.android.ipbandroidview.utils

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
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
        textView.setTextColor(Color.RED)
        textView.text = String.format(
            textView.context.getString(R.string.negative_sum), kotlin.math.abs(quantity)
        )
    } else {
        textView.setTextColor(Color.GREEN)
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
        ScreenState.ERROR -> mainContent.visibility = View.VISIBLE
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
