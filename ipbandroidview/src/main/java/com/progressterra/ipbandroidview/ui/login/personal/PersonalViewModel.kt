package com.progressterra.ipbandroidview.ui.login.personal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.progressterra.android.api.a.remoteData.scrm.models.responses.CitiesListResponse
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.BonusesApi
import com.progressterra.ipbandroidapi.interfaces.client.login.LoginApi
import com.progressterra.ipbandroidapi.interfaces.client.login.models.PersonalInfo
import com.progressterra.ipbandroidapi.localdata.shared_pref.models.SexType
import com.progressterra.ipbandroidapi.remoteData.models.base.GlobalResponseStatus
import com.progressterra.ipbandroidview.ui.login.OnLoginFlowFinishListener
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.extensions.notifyObserver
import kotlinx.coroutines.launch

class PersonalViewModel(val onLoginFlowFinishListener: OnLoginFlowFinishListener?) : ViewModel() {
    val personalInfo = MutableLiveData(PersonalInfo())
    val personalDataIsValid = MutableLiveData(false)
    val citiesList = MutableLiveData<List<CitiesListResponse.City>>()

    private val _toastText = MutableLiveData<Event<String>>()
    val toastText: LiveData<Event<String>> = _toastText

    init {
        val api = LoginApi.newInstance()
        viewModelScope.launch {
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
        viewModelScope.launch {

            // запрашиваем токен, чтобы он сохранился в префах, так как используется в послед запросах
            bonusesApi.getAccessToken().let {
                if (it.globalResponseStatus == GlobalResponseStatus.ERROR) {
                    _toastText.postValue(Event("Ошибка сети"))
                    return@launch
                }
            }

            loginApi.addClientInfo(personalInfo.value!!).let {
                if (it.globalResponseStatus == GlobalResponseStatus.ERROR) {
                    _toastText.postValue(Event("Ошибка при указании пользовательских данных"))
                    return@launch
                }
            }
            loginApi.addCity(personalInfo.value!!.city!!).let {
                if (it.globalResponseStatus == GlobalResponseStatus.ERROR) {
                    _toastText.postValue(Event("Ошибка при указании пользовательских данных"))
                    return@launch
                }
            }
            loginApi.addEmail(personalInfo.value!!.email!!).let {
                if (it.globalResponseStatus == GlobalResponseStatus.ERROR) {
                    _toastText.postValue(Event("Ошибка при указании пользовательских данных"))
                    return@launch
                }
            }

            loginApi.confirmEmail(personalInfo.value!!.email!!)
            onLoginFlowFinishListener?.onLoginFinish()
        }
    }

}