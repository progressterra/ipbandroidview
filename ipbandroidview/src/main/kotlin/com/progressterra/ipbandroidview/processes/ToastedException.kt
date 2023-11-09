package com.progressterra.ipbandroidview.processes

import androidx.annotation.StringRes

open class ToastedException(
    @StringRes val stringId: Int,
    var customMsg: String? = null
) : Exception() {


    constructor(custom: String) : this(0) {
        customMsg = custom
    }
}