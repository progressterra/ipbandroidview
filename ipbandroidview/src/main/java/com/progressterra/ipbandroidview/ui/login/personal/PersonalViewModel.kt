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
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.ui.login.OnLoginFlowFinishListener
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.extensions.notifyObserver
import kotlinx.coroutines.launch

class PersonalViewModel(val onLoginFlowFinishListener: OnLoginFlowFinishListener?) : ViewModel() {
    val personalInfo = MutableLiveData(PersonalInfo())
    val personalDataIsValid = MutableLiveData(false)
    val citiesList = MutableLiveData<List<CitiesListResponse.City>>()

    private val _toastTextRes = MutableLiveData<Event<Int>>()
    val toastTextRes: LiveData<Event<Int>> = _toastTextRes

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
                    _toastTextRes.postValue(Event(R.string.Network_error))
                    return@launch
                }
            }

            personalInfo.value?.let { personalInfo ->
                loginApi.addClientInfo(personalInfo).let {
                    if (it.globalResponseStatus == GlobalResponseStatus.ERROR) {
                        _toastTextRes.postValue(Event(R.string.user_data_error))
                        return@launch
                    }
                }

                personalInfo.city?.let { city ->
                    loginApi.addCity(city).let {
                        if (it.globalResponseStatus == GlobalResponseStatus.ERROR) {
                            _toastTextRes.postValue(Event(R.string.user_data_error))
                            return@launch
                        }
                    }
                }

                personalInfo.email?.let { email ->
                    loginApi.addEmail(email).let {
                        if (it.globalResponseStatus == GlobalResponseStatus.ERROR) {
                            _toastTextRes.postValue(Event(R.string.user_data_error))
                            return@launch
                        }
                    }

                    loginApi.confirmEmail(email)
                }
            }
            onLoginFlowFinishListener?.onLoginFinish()
        }
    }

}