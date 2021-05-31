package com.progressterra.ipbandroidview.ui.login.personal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidapi.localdata.shared_pref.models.SexType
import com.progressterra.ipbandroidview.models.ui.PersonalInfo
import com.progressterra.ipbandroidview.utils.extensions.notifyObserver

class PersonalViewModel : ViewModel() {
    val personalInfo = MutableLiveData<PersonalInfo>(PersonalInfo())
    val personalDataIsValid = MutableLiveData(false)


    fun updateFirstName(newName: String) {
        personalInfo.value?.name = newName
        personalDataIsValid.postValue(personalInfo.value?.infoIsValid())
        personalInfo.notifyObserver()
    }

    fun updateLastName(newLastName: String) {
        personalInfo.value?.lastname = newLastName
        personalDataIsValid.postValue(personalInfo.value?.infoIsValid())
        personalInfo.notifyObserver()

    }

    fun updateSex(sexType: SexType) {
        personalInfo.value?.sexType = sexType
        personalDataIsValid.postValue(personalInfo.value?.infoIsValid())

    }

    fun updateBirthdate(day: Int, month: Int, year: Int) {
        personalInfo.value?.birthdate = "$year:$month:$day"
        personalDataIsValid.postValue(personalInfo.value?.infoIsValid())
        personalInfo.notifyObserver()
    }

    fun updateEmail(newEmail: String) {
        personalInfo.value?.email = newEmail
        personalDataIsValid.postValue(personalInfo.value?.infoIsValid())
        personalInfo.notifyObserver()
    }

    fun updateCity(newCity: String) {
        personalInfo.value?.city = newCity
        personalDataIsValid.postValue(personalInfo.value?.infoIsValid())
    }

}