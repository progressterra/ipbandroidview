package com.progressterra.ipbandroidview.models.ui

import com.progressterra.ipbandroidapi.localdata.shared_pref.models.SexType

class PersonalInfo {
    var name: String? = null
        set(value) {
            field = value
            nameIsValid = !field.isNullOrBlank()
        }

    var lastname: String? = null
        set(value) {
            field = value
            lastNameIsValid = !field.isNullOrBlank()
        }


    var email: String? = null
        set(value) {
            field = value
            emailIsValid = !field.isNullOrBlank() && (Regex(".+@.+\\..+")).matches(field ?: "")
        }


    var birthdate: String? = null
        set(value) {
            field = value
            birthDateIsValid = true
        }

    var sexType: SexType? = null
        set(value) {
            field = value
            sexTypeSelected = true
        }

    var city: String? = null
        set(value) {
            field = value
            cityIsValid = !field.isNullOrBlank()
        }

    var nameIsValid = false
    var lastNameIsValid = false
    var emailIsValid = false
    var sexTypeSelected = false
    var birthDateIsValid = false
    var cityIsValid = false


    fun infoIsValid(): Boolean {
        return nameIsValid && lastNameIsValid && emailIsValid && sexTypeSelected && birthDateIsValid && cityIsValid
    }
}