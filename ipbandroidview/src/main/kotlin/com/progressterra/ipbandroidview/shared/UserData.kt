package com.progressterra.ipbandroidview.shared

import com.chibatching.kotpref.KotprefModel
import com.chibatching.kotpref.gsonpref.gsonPref
import com.progressterra.ipbandroidview.entities.Address
import com.progressterra.ipbandroidview.entities.Citizenship
import com.progressterra.ipbandroidview.shared.IpbAndroidViewSettings.DEFAULT_ID

/**
 * Object used to store user data in shared preferences using
 * @property idUnique - unique id of user
 * @property deviceId - unique id of device
 * @property clientExist - flag that shows if user logged in
 * @property phone - user phone
 * @property email - user email
 * @property shippingAddress - user shipping address
 * @property userName - user name
 * @property citizenship - user citizenship
 * @property cartCounter - counter of goods in cart
 * @property fcmToken - latest actual fcm token
 * @property fcmTokenSent - last time when fcm token was sent to server
 * @property sex - sex, 0 - null, 1 - male, 2 - female
 * @property dateOfBirthday - birthday, stored in ZDT ISO
 */
data object UserData : KotprefModel() {

    var idUnique by stringPref(DEFAULT_ID)
    var deviceId by stringPref(DEFAULT_ID)
    var clientExist by booleanPref()
    var phone by stringPref()
    var email by stringPref()
    var shippingAddress by gsonPref(Address())
    var userName by gsonPref(UserName())
    var citizenship by gsonPref(Citizenship())
    var cartCounter by intPref()
    var fcmToken by stringPref()
    var fcmTokenSent by booleanPref()
    var sex by intPref()
    var dateOfBirthday by stringPref()

    fun clearUser() {
        sex = 0
        cartCounter = 0
        idUnique = DEFAULT_ID
        deviceId = DEFAULT_ID
        clientExist = false
        citizenship = Citizenship()
        phone = ""
        email = ""
        userName = UserName()
        dateOfBirthday = ""
        shippingAddress = Address()
    }
}