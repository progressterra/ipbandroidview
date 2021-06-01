package com.progressterra.ipbandroidview.ui.login.personal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.progressterra.android.api.a.remoteData.scrm.models.responses.CitiesListResponse
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.BonusesApi
import com.progressterra.ipbandroidapi.interfaces.client.login.LoginApi
import com.progressterra.ipbandroidapi.interfaces.client.login.models.PersonalInfo
import com.progressterra.ipbandroidapi.localdata.shared_pref.models.SexType
import com.progressterra.ipbandroidview.utils.extensions.notifyObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PersonalViewModel : ViewModel() {
    val personalInfo = MutableLiveData(PersonalInfo())
    val personalDataIsValid = MutableLiveData(false)
    val citiesList = MutableLiveData<List<CitiesListResponse.City>>()

    init {
        val api = LoginApi.newInstance()
        CoroutineScope(Job()).launch {
            api.getCitiesList().let {
                citiesList.postValue(it.responseBody?.dataList?.filter { city -> !city.name.isNullOrEmpty() })
            }
        }
    }

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
        personalInfo.value?.birthdate = "$year-$month-$day"
        personalDataIsValid.postValue(personalInfo.value?.infoIsValid())
        personalInfo.notifyObserver()
    }

    fun updateEmail(newEmail: String) {
        personalInfo.value?.email = newEmail
        personalDataIsValid.postValue(personalInfo.value?.infoIsValid())
        personalInfo.notifyObserver()
    }

    fun updateCity(newCity: CitiesListResponse.City) {
        personalInfo.value?.city = newCity
        personalDataIsValid.postValue(personalInfo.value?.infoIsValid())
    }

    fun addPersonalInfo() {
        val loginApi = LoginApi.newInstance()
        val bonusesApi = BonusesApi.getInstance()
        CoroutineScope(Job()).launch {
            bonusesApi.getAccessToken()
            loginApi.addClientInfo(personalInfo.value!!)
            loginApi.addCity(personalInfo.value!!.city!!)
            loginApi.addEmail(personalInfo.value!!.email!!)
            loginApi.confirmEmail(personalInfo.value!!.email!!)
        }
    }

}