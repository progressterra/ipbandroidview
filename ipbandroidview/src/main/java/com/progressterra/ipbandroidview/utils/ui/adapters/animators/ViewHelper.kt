package com.progressterra.ipbandroidview.utils.ui.adapters.animators

import android.view.View

object ViewHelper {
    @JvmStatic
    fun clear(v: View) {
        v.apply {
            alpha = 1f
            scaleY = 1f
            scaleX = 1f
            translationY = 0f
            translationX = 0f
            rotation = 0f
            rotationY = 0f
            rotationX = 0f
            pivotY = v.measuredHeight / 2f
            pivotX = v.measuredWidth / 2f
            animate().setInterpolator(null).startDelay = 0
        }
    }
}