package com.progressterra.ipbandroidview.utils.ui.binding

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.progressterra.ipbandroidapi.utils.extentions.orIfNull
import com.progressterra.ipbandroidview.R

@BindingAdapter(value = ["url", "placeholder"], requireAll = false)
fun setImageFromUrl(iv: ImageView, url: String?, placeholder: Drawable?) {
    if (url.isNullOrBlank()) {
        iv.setImageResource(R.drawable.image_placeholder)
    } else {
        val glide = Glide.with(iv)
            .load(url)
            .fitCenter()
            .circleCrop()

        if (placeholder == null) {
            glide.placeholder(R.drawable.image_placeholder)
        } else {
            glide.placeholder(placeholder)
        }

        glide.into(iv)
    }
}

@BindingAdapter(value = ["circleUrl", "placeholder"], requireAll = false)
fun setImageCircle(iv: ImageView, url: String?, placeholder: Drawable?) {
    if (url.isNullOrBlank()) {
        iv.setImageDrawable(placeholder)
    } else {
        val requestListener = object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                Log.e("setImageCircle", e?.message ?: e.toString())
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }
        }

        Glide.with(iv)
            .load(url)
            .fitCenter()
            .circleCrop()
            .placeholder(placeholder)
            .addListener(requestListener)
            .into(iv)

    }
}

@BindingAdapter(value = ["roundUrl", "cornerRadius", "placeholder"], requireAll = false)
fun setImageRounded(iv: ImageView, url: String?, radius: Int, placeholder: Drawable?) {
    if (url.isNullOrBlank()) {
        if (placeholder != null) {
            iv.setImageDrawable(placeholder)
        }
    } else {
        val requestListener = object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                Log.e("setImageRounded", e?.message ?: e.toString())
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }
        }

        Glide.with(iv)
            .load(url)
            .fitCenter()
            .centerCrop()
            .transform(RoundedCorners(radius.takeIf { it != 0 }.orIfNull { 10 }))
            .addListener(requestListener)
            .into(iv)
    }
}