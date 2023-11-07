package com.progressterra.ipbandroidview.processes

import androidx.annotation.StringRes

open class ToastedException(@StringRes val stringId: Int) : Exception() {

    private var customMsg: String? = null

    constructor(custom: String) : this(0) {
        customMsg = custom
    }
}