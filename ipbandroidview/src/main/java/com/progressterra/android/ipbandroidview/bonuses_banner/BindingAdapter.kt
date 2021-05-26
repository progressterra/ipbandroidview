package com.progressterra.android.ipbandroidview.bonuses_banner

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:visibility")
fun visibility(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("app:textFromInt")
fun textFromInt(view: TextView, text: Int) {
    view.text = text.toString()
}
