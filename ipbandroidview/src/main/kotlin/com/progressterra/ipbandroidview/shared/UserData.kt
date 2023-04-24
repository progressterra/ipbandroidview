package com.progressterra.ipbandroidview.shared

import com.chibatching.kotpref.KotprefModel
import com.chibatching.kotpref.gsonpref.gsonPref
import com.progressterra.ipbandroidview.entities.AddressUI

@Suppress("unused")
object UserData : KotprefModel() {

    var idUnique by stringPref()
    var deviceId by stringPref()
    var clientExist by booleanPref()
    var phone by stringPref()
    var email by stringPref()
    var address by gsonPref(AddressUI())
    var userName by gsonPref(UserName())
    var citizenship by stringPref()
    var passport by stringPref()
    var passportProvider by stringPref()
    var passportProviderCode by stringPref()
    var patent by stringPref()
    var dateOfBirthday by longPref()
    var supportChatId by stringPref()

    fun clearUser() {
        idUnique = ""
        deviceId = ""
        clientExist = false
        phone = ""
        email = ""
        userName = UserName()
        dateOfBirthday = 0L
        address = AddressUI()
        supportChatId = ""
    }

    fun needDetails(): Boolean =
        email.isBlank() || dateOfBirthday == 0L || userName.isEmpty()

    fun needAddress(): Boolean = address.isEmpty()
}