package com.progressterra.ipbandroidview.ui.login.personal

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.BonusesApi
import com.progressterra.ipbandroidapi.interfaces.client.login.LoginApi
import com.progressterra.ipbandroidapi.interfaces.client.login.models.PersonalInfo
import com.progressterra.ipbandroidapi.localdata.shared_pref.models.SexType
import com.progressterra.ipbandroidapi.remoteData.models.base.GlobalResponseStatus
import com.progressterra.ipbandroidapi.remoteData.scrm.models.responses.CitiesListResponse
import com.progressterra.ipbandroidview.MainNavGraphDirections
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.ui.base.BaseViewModel
import com.progressterra.ipbandroidview.ui.login.settings.LoginKeys
import com.progressterra.ipbandroidview.ui.login.settings.PersonalSettings
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ScreenState
import com.progressterra.ipbandroidview.utils.ToastBundle
import com.progressterra.ipbandroidview.utils.extensions.loadingResult
import com.progressterra.ipbandroidview.utils.extensions.notifyObserver
import kotlinx.coroutines.launch

internal class PersonalViewModel(
    val personalSettings: PersonalSettings,
    private val newLoginFlow: Boolean
) : BaseViewModel() {

    val personalInfo = MutableLiveData(PersonalInfo())
    val personalDataIsValid = MutableLiveData(false)
    val citiesList = MutableLiveData<List<CitiesListResponse.City>>()

    private val _setFragmentResult = MutableLiveData<Event<Bundle>>()
    val setFragmentResult: LiveData<Event<Bundle>> = _setFragmentResult

    private val _popBackStack = MutableLiveData<Event<Boolean>>()
    val popBackStack: LiveData<Event<Boolean>> = _popBackStack

    init {
        val api = LoginApi.newInstance()
        viewModelScope.launch {
            api.getCitiesList().let {
                citiesList.postValue(it.responseBody?.dataList?.filter { city -> !city.name.isNullOrEmpty() })
            }
        }

        if (!personalSettings.enableSex) {
            personalInfo.value?.sexType = SexType.NONE
            personalDataIsValid.postValue(personalInfo.value?.infoIsValid())
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

    fun updateBirthdate(
        day: Int,
        month: Int,
        year: Int
    ) {
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
            _screenState.postValue(ScreenState.LOADING)

            // запрашиваем токен, чтобы он сохранился в префах, так как используется в послед запросах
            bonusesApi.getAccessToken().let {
                if (it.globalResponseStatus == GlobalResponseStatus.ERROR) {
                    _toastBundle.postValue(Event(ToastBundle(R.string.Network_error)))
                    _screenState.postValue(ScreenState.ERROR)
                    return@launch
                }
            }

            personalInfo.value?.let { personalInfo ->
                loginApi.addClientInfo(personalInfo).let {
                    if (it.globalResponseStatus == GlobalResponseStatus.ERROR) {
                        _toastBundle.postValue(Event(ToastBundle(R.string.user_data_error)))
                        _screenState.postValue(ScreenState.ERROR)
                        return@launch
                    }
                }

                personalInfo.city?.let { city ->
                    loginApi.addCity(city).let {
                        if (it.globalResponseStatus == GlobalResponseStatus.ERROR) {
                            _toastBundle.postValue(Event(ToastBundle(R.string.user_data_error)))
                            _screenState.postValue(ScreenState.ERROR)
                            return@launch
                        }
                    }
                }

                personalInfo.email?.let { email ->
                    loginApi.addEmail(email).let {
                        if (it.globalResponseStatus == GlobalResponseStatus.ERROR) {
                            _toastBundle.postValue(Event(ToastBundle(R.string.user_data_error)))
                            _screenState.postValue(ScreenState.ERROR)
                            return@launch
                        }
                    }
                    loginApi.confirmEmail(email)
                }
            }
            _setFragmentResult.postValue(Event(bundleOf(LoginKeys.AUTH_DONE to true)))

            _screenState.postValue(ScreenState.DEFAULT)
            if (newLoginFlow)
                _popBackStack.postValue(Event(true))
            else
                _action.postValue(Event(MainNavGraphDirections.actionGlobalBaseFlow()))
        }
    }

    fun skipRegistration() {
        _setFragmentResult.postValue(Event(bundleOf(LoginKeys.AUTH_SKIP to true)))
        if (newLoginFlow)
            _popBackStack.value = Event(true)
        else
            _action.postValue(Event(MainNavGraphDirections.actionGlobalBaseFlow()))
    }
}