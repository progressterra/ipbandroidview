package com.progressterra.ipbandroidview.ui.login.personal

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidapi.api.PersonalInfo
import com.progressterra.ipbandroidapi.api.cities.CitiesRepository
import com.progressterra.ipbandroidapi.api.cities.model.CitiesListResponse
import com.progressterra.ipbandroidapi.api.cities.model.City
import com.progressterra.ipbandroidapi.api.city.CityRepository
import com.progressterra.ipbandroidapi.api.city.model.AddCityRequest
import com.progressterra.ipbandroidapi.api.ibonus.IBonusRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.ClientInfoRequest
import com.progressterra.ipbandroidapi.user.SexType
import com.progressterra.ipbandroidview.MainNavGraphDirections
import com.progressterra.ipbandroidview.ui.base.BaseViewModel
import com.progressterra.ipbandroidview.ui.login.settings.LoginKeys
import com.progressterra.ipbandroidview.ui.login.settings.PersonalSettings
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ScreenState
import com.progressterra.ipbandroidview.utils.extensions.notifyObserver
import kotlinx.coroutines.launch

internal class PersonalViewModel(
    val personalSettings: PersonalSettings,
    private val newLoginFlow: Boolean,
    private val citiesRepository: CitiesRepository,
    private val sCRMRepository: SCRMRepository,
    private val iBonusRepository: IBonusRepository,
    private val cityRepository: CityRepository
) : BaseViewModel() {

    val personalInfo = MutableLiveData(PersonalInfo())
    val personalDataIsValid = MutableLiveData(false)
    val citiesList = MutableLiveData<List<City>>()

    private val _setFragmentResult = MutableLiveData<Event<Bundle>>()
    val setFragmentResult: LiveData<Event<Bundle>> = _setFragmentResult

    private val _popBackStack = MutableLiveData<Event<Boolean>>()
    val popBackStack: LiveData<Event<Boolean>> = _popBackStack

    init {
        viewModelScope.launch {
            citiesRepository.getCities().map {
                citiesList.postValue(it)
            }
        }

        if (!personalSettings.enableSex) {
            personalInfo.value?.sexType = SexType.NONE
            personalDataIsValid.postValue(
                personalInfo.value?.infoIsValid(
                    includeName = true,
                    includeSurname = true,
                    includeSex = false,
                    includeBirthDate = true,
                    includeCity = false,
                    includeEmail = false,
                    includePatronymic = false
                )
            )
        }
    }

    fun updateFirstName(newName: String) {
        personalInfo.value?.name = newName
        personalDataIsValid.postValue(
            personalInfo.value?.infoIsValid(
                includeName = true,
                includeSurname = true,
                includeSex = false,
                includeBirthDate = true,
                includeCity = false,
                includeEmail = false,
                includePatronymic = false
            )
        )
        personalInfo.notifyObserver()
    }

    fun updateLastName(newLastName: String) {
        personalInfo.value?.lastname = newLastName
        personalDataIsValid.postValue(
            personalInfo.value?.infoIsValid(
                includeName = true,
                includeSurname = true,
                includeSex = false,
                includeBirthDate = true,
                includeCity = false,
                includeEmail = false,
                includePatronymic = false
            )
        )
        personalInfo.notifyObserver()
    }

    fun updateSex(sexType: SexType) {
        personalInfo.value?.sexType = sexType
        personalDataIsValid.postValue(
            personalInfo.value?.infoIsValid(
                includeName = true,
                includeSurname = true,
                includeSex = false,
                includeBirthDate = true,
                includeCity = false,
                includeEmail = false,
                includePatronymic = false
            )
        )
    }

    fun updateBirthdate(
        day: Int,
        month: Int,
        year: Int
    ) {
        personalInfo.value?.birthdate = "$year-$month-$day"
        personalDataIsValid.postValue(
            personalInfo.value?.infoIsValid(
                includeName = true,
                includeSurname = true,
                includeSex = false,
                includeBirthDate = true,
                includeCity = false,
                includeEmail = false,
                includePatronymic = false
            )
        )
        personalInfo.notifyObserver()
    }

    //Will not invoked if enableEmail is false
    fun updateEmail(newEmail: String) {
        personalInfo.value?.email = newEmail
        personalDataIsValid.postValue(
            personalInfo.value?.infoIsValid(
                includeName = true,
                includeSurname = true,
                includeSex = false,
                includeBirthDate = true,
                includeCity = false,
                includeEmail = false,
                includePatronymic = false
            )
        )
        personalInfo.notifyObserver()
    }

    fun updateCity(newCity: City) {
        personalInfo.value?.city = CitiesListResponse.Data(
            newCity.idUnique,
            newCity.latitudeCenter,
            newCity.longitudeCenter,
            newCity.name,
            newCity.radius
        )
        personalDataIsValid.postValue(
            personalInfo.value?.infoIsValid(
                includeName = true,
                includeSurname = true,
                includeSex = false,
                includeBirthDate = true,
                includeCity = false,
                includeEmail = false,
                includePatronymic = false
            )
        )
    }

    fun addPersonalInfo() {
        viewModelScope.launch {
            _screenState.postValue(ScreenState.LOADING)

            // запрашиваем токен, чтобы он сохранился в префах, так как используется в послед запросах
            val token = sCRMRepository.getAccessToken().map { token ->
                personalInfo.value?.let { info ->
                    sCRMRepository.setPersonalInfo(
                        token,
                        info.sexType ?: SexType.NONE,
                        info.lastname ?: "",
                        info.name ?: "",
                        info.patronymic ?: "",
                        info.birthdate ?: "",
                        ""
                    )
                    info.city?.let {
                        cityRepository.setCity(
                            token,
                            cityName = it.name ?: "",
                            idrfCity = it.idUnique,
                            latitude = it.latitudeCenter.toDouble(),
                            longitude = it.longitudeCenter.toDouble()
                        )
                    }
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