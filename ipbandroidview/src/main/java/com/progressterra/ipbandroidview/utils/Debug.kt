package com.progressterra.ipbandroidview.utils

import android.util.Log

fun log(message: String, tag: String = "myTag", ex: Exception? = null) {
    Log.d(tag, message, ex)
}