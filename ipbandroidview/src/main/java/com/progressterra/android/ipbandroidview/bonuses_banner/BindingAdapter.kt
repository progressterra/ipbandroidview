package com.progressterra.android.ipbandroidview.bonuses_banner

import android.view.View
import androidx.databinding.BindingAdapter


    @BindingAdapter("app:visibility")
    fun visibility(view: View, isVisible: Boolean) {
        view.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
