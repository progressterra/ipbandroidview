package com.progressterra.ipbandroidview.utils.ui.binding

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.progressterra.ipbandroidapi.utils.extentions.orIfNull
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.utils.log
import com.progressterra.ripcurl.utils.view.image_view.CircleTransform
import com.progressterra.ripcurl.utils.view.image_view.RoundCornersTransform
import com.squareup.picasso.Callback
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

@BindingAdapter(value = ["circleUrl", "placeholder"], requireAll = false)
fun setImageCircle(iv: ImageView, url: String?, placeholder: Drawable?) {
    if (url.isNullOrBlank()) {
        iv.setImageDrawable(placeholder)
    } else {
        val picasso = Picasso.get()
            .load(url)
            .fit()
            .centerCrop()
            .transform(CircleTransform())

        if (placeholder != null) {
            picasso.placeholder(placeholder)
        } else {
            picasso.noPlaceholder()
        }

        picasso.into(iv, object : Callback {
            override fun onSuccess() {
            }

            override fun onError(e: Exception?) {
                Log.e("setImageCircle", e?.localizedMessage ?: e.toString())
            }
        })
    }
}

@BindingAdapter(value = ["roundUrl", "cornerRadius", "placeholder"], requireAll = false)
fun setImageRounded(iv: ImageView, url: String?, radius: Int, placeholder: Drawable?) {
    if (url.isNullOrBlank()) {
        if (placeholder != null) {
            iv.setImageDrawable(placeholder)
        }
    } else {
        val picasso = Picasso.get()
            .load(url)
            .fit()
            .centerCrop()
            .transform(
                RoundCornersTransform(
                    radius.takeIf { it != 0 }.orIfNull { 10 }.toFloat()
                )
            )

        if (placeholder != null) {
            picasso.placeholder(placeholder)
        } else {
            picasso.noPlaceholder()
        }

        picasso.into(iv, object : Callback {
            override fun onSuccess() {}

            override fun onError(e: Exception?) {
                Log.e("setImageRounded", e?.localizedMessage ?: e.toString())
            }
        })
    }
}