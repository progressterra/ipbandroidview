package com.progressterra.ipbandroidview.data.prefs

import com.chibatching.kotpref.KotprefModel

object UserDataLocal : KotprefModel() {
    var city by stringPref("")
}