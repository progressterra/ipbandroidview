package com.progressterra.ipbandroidview.utils.extensions

import android.content.res.Resources

val Int.dpToPx: Int
    get() = (this.times(Resources.getSystem().displayMetrics.density)).toInt()

val Int.pxToDp: Int
    get() = (this.div(Resources.getSystem().displayMetrics.density)).toInt()