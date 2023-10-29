package com.progressterra.ipbandroidview.processes

import androidx.annotation.StringRes

open class ToastedException(@StringRes val stringId: Int) : Exception()