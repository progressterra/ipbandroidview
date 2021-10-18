package com.progressterra.ipbandroidview.utils.ui.adapters.animators

import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Interpolator
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.ipbandroidview.utils.extensions.dpToPx

open class SlideInRightAnimator : BaseItemAnimator {
    constructor()

    constructor(interpolator: Interpolator) {
        this.interpolator = interpolator
    }

    constructor(padding: Int) {
        mPadding = padding.dpToPx
    }

    constructor(padding: Int, interpolator: Interpolator) {
        this.interpolator = interpolator
        mPadding = padding.dpToPx
    }

    private var mPadding: Int? = null

    override fun animateRemoveImpl(holder: RecyclerView.ViewHolder) {
        holder.itemView.animate().apply {
            translationX(holder.itemView.rootView.width.toFloat())
            duration = removeDuration
            interpolator = this@SlideInRightAnimator.interpolator
            setListener(DefaultRemoveAnimatorListener(holder))
            startDelay = getRemoveDelay(holder)
        }.start()
    }

    override fun preAnimateAddImpl(holder: RecyclerView.ViewHolder) {
        holder.itemView.translationY = holder.itemView.height.plus(mPadding ?: 0).toFloat()
    }

    override fun animateAddImpl(holder: RecyclerView.ViewHolder) {
        holder.itemView.animate().apply {
            translationY(0f)
            duration = addDuration
            interpolator = AccelerateDecelerateInterpolator()
            setListener(DefaultAddAnimatorListener(holder))
            startDelay = getAddDelay(holder)
        }.start()
    }
}