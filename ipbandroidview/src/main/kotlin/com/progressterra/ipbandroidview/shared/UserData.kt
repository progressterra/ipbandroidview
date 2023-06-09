package com.progressterra.ipbandroidview.shared

import com.chibatching.kotpref.KotprefModel
import com.chibatching.kotpref.gsonpref.gsonPref
import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.entities.Citizenship
import com.progressterra.ipbandroidview.shared.Constants.DEFAULT_ID

object UserData : KotprefModel() {

    var idUnique by stringPref(DEFAULT_ID)
    var deviceId by stringPref(DEFAULT_ID)
    var clientExist by booleanPref()
    var phone by stringPref()
    var email by stringPref()
    var address by gsonPref(AddressUI())
    var userName by gsonPref(UserName())
    var citizenship by gsonPref(Citizenship())
    var dateOfBirthday by longPref()
    var supportChatId by stringPref()

    fun clearUser() {
        idUnique = DEFAULT_ID
        deviceId = DEFAULT_ID
        clientExist = false
        citizenship = Citizenship()
        phone = ""
        email = ""
        userName = UserName()
        dateOfBirthday = 0L
        address = AddressUI()
        supportChatId = ""
    }
}