package com.progressterra.ipbandroidview.shared

import android.util.Log
import com.progressterra.ipbandroidview.IpbAndroidViewSettings

fun log(tag: String, msg: String) {
    if (IpbAndroidViewSettings.DEBUG) Log.d(tag, msg)
}