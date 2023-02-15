package com.progressterra.ipbandroidview.data

import com.chibatching.kotpref.KotprefModel
import com.chibatching.kotpref.gsonpref.gsonPref
import com.progressterra.ipbandroidview.model.AddressUI

@Suppress("unused")
object UserData : KotprefModel() {

    var idUnique by stringPref()
    var deviceId by stringPref()
    var clientExist by booleanPref()
    var phone by stringPref()
    var email by stringPref()
    var address by gsonPref(AddressUI())
    var userName by gsonPref(UserName())
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
        this.email.isBlank() || this.dateOfBirthday == 0L || this.userName.isEmpty()

    fun needAddress(): Boolean = this.address.isEmpty()
}