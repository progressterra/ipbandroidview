package com.progressterra.ipbandroidview.ui.chat.utils

import android.view.animation.Interpolator
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.ipbandroidview.utils.ui.adapters.animators.BaseItemAnimator

class MessagesAnimator : BaseItemAnimator {
    constructor()
    constructor(interpolator: Interpolator) {
        this.interpolator = interpolator
    }

    override fun animateRemoveImpl(holder: RecyclerView.ViewHolder) {
        holder.itemView.animate().apply {
            translationY(holder.itemView.height.toFloat())
            alpha(0f)
            duration = removeDuration
            interpolator = this@MessagesAnimator.interpolator
            setListener(DefaultRemoveAnimatorListener(holder))
            startDelay = getRemoveDelay(holder)
        }.start()
    }

    override fun preAnimateAddImpl(holder: RecyclerView.ViewHolder) {
        holder.itemView.translationY = holder.itemView.height.toFloat()
        holder.itemView.alpha = 0f
        holder.itemView.scaleX = 0.5f
        holder.itemView.scaleY = 0.5f
    }

    override fun animateAddImpl(holder: RecyclerView.ViewHolder) {
        holder.itemView.animate().apply {
            translationY(0f)
            alpha(1f)
            scaleY(1f)
            scaleX(1f)
            duration = addDuration
            interpolator = this@MessagesAnimator.interpolator
            setListener(DefaultAddAnimatorListener(holder))
        }.start()
    }
}