package com.progressterra.ipbandroidview.shared

import com.chibatching.kotpref.KotprefModel
import com.chibatching.kotpref.gsonpref.gsonPref
import com.progressterra.ipbandroidview.IpbAndroidViewSettings.DEFAULT_ID
import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.entities.Citizenship

object UserData : KotprefModel() {

    var idUnique by stringPref(DEFAULT_ID)
    var deviceId by stringPref(DEFAULT_ID)
    var clientExist by booleanPref()
    var phone by stringPref()
    var email by stringPref()
    var shippingAddress by gsonPref(AddressUI())
    var userName by gsonPref(UserName())
    var citizenship by gsonPref(Citizenship())
    var cartCounter by intPref()
    var fcmToken by stringPref()
    var fcmTokenSent by booleanPref()
    var readyToMeet by booleanPref()
    var sex by intPref()
    /**
     * ZDT ISO
     */
    var dateOfBirthday by stringPref()

    fun clearUser() {
        sex = 0
        cartCounter = 0
        idUnique = DEFAULT_ID
        deviceId = DEFAULT_ID
        clientExist = false
        readyToMeet = false
        citizenship = Citizenship()
        phone = ""
        email = ""
        userName = UserName()
        dateOfBirthday = ""
        shippingAddress = AddressUI()
    }
}