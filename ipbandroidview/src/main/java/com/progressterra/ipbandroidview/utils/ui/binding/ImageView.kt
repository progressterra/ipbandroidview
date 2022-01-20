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
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.progressterra.ipbandroidapi.utils.extentions.orIfNull
import com.progressterra.ipbandroidview.R

@BindingAdapter(value = ["url", "isShimmerPlaceholder"], requireAll = false)
fun setImageFromUrl(iv: ImageView, url: String?, isShimmerPlaceholder: Boolean?) {
    if (url.isNullOrBlank()) {
        iv.setImageResource(R.drawable.image_placeholder)
    } else {
        val glide = Glide.with(iv)
            .load(url)
            .fitCenter()
            .error(R.drawable.image_placeholder)

        if (isShimmerPlaceholder == null || isShimmerPlaceholder == false) {
            glide.placeholder(R.drawable.image_placeholder)
        } else {
            val shimmer = Shimmer.AlphaHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
                .setDuration(1800) // how long the shimmering animation takes to do one full sweep
                .setBaseAlpha(0.4f) //the alpha of the underlying children
                .setHighlightAlpha(0.35f) // the shimmer alpha amount
                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setAutoStart(true)
                .build()

            val shimmerDrawable = ShimmerDrawable().apply {
                setShimmer(shimmer)
            }

            glide.placeholder(shimmerDrawable)
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