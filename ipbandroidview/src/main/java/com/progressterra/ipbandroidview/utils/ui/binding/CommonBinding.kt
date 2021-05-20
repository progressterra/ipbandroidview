package com.progressterra.ipbandroidview.utils.ui.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visibility")
fun visibility(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}
