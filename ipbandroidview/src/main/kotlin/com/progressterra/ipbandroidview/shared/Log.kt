package com.progressterra.ipbandroidview.shared

import android.util.Log
import com.progressterra.ipbandroidview.IpbAndroidViewSettings

fun log(tag: String, msg: String) {
    if (IpbAndroidViewSettings.DEBUG) {
        val maxMsgLength = 2000
        if (msg.length > maxMsgLength) {
            Log.d(tag, msg.substring(0, maxMsgLength))
            log(tag, msg.substring(maxMsgLength))
        } else {
            Log.d(tag, msg)
        }
    }
}