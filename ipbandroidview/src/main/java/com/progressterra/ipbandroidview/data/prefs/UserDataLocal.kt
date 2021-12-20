package com.progressterra.ipbandroidview.data.prefs

import com.chibatching.kotpref.KotprefModel

object UserDataLocal : KotprefModel() {
    var city by stringPref("")
    var avatarUrl by stringPref("")

    fun clearUser() {
        city = ""
        avatarUrl = ""
    }
}