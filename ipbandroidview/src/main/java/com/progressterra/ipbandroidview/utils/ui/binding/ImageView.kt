package com.progressterra.ipbandroidview.utils.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.progressterra.ipbandroidview.R
import com.squareup.picasso.Picasso

@BindingAdapter("url")
fun setImageFromUrl(iv: ImageView, url: String?) {
    if (url.isNullOrBlank())
        iv.setImageResource(R.drawable.image_placeholder)
    else
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.image_placeholder)
            .into(iv)
}