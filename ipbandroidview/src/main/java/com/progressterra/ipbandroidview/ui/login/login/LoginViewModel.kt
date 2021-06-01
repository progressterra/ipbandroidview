package com.progressterra.ipbandroidview.ui.login.login

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.progressterra.ipbandroidapi.interfaces.client.login.LoginApi
import com.progressterra.ipbandroidapi.remoteData.models.base.GlobalResponseStatus
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.ui.login.OnLoginFlowFinishListener
import com.progressterra.ipbandroidview.ui.login.confirm.ConfirmFragment
import com.progressterra.ipbandroidview.ui.login.country.CountryFragment
import com.progressterra.ipbandroidview.ui.login.country.enums.Country
import com.progressterra.ipbandroidview.utils.Event
import kotlinx.coroutines.launch

internal class LoginViewModel(
    var selectedCountry: String,
    private val onLoginFlowFinishListener: OnLoginFlowFinishListener?
) : ViewModel() {

    private val _screenState = MutableLiveData(ScreenState.DEFAULT)
    val screenState:LiveData<ScreenState> = _screenState

    private val country: Country = Country.valueOf(selectedCountry)

    private val _nextFragment = MutableLiveData<Event<Fragment>>()
    val nextFragment: LiveData<Event<Fragment>> = _nextFragment

    private val _countryText = MutableLiveData(country)
    val countryText: LiveData<String> = _countryText.map {
        "${it.titleRu} (+ ${it.phoneCode})"
    }

    private val _phoneText = MutableLiveData("")
    val phoneText: LiveData<String> = _phoneText

    private val _toastStringInt = MutableLiveData<Event<Int>>()
    val toastStringInt: LiveData<Event<Int>> = _toastStringInt

    private val _toastText = MutableLiveData<Event<String>>()
    val toastText: LiveData<Event<String>> = _toastText

    var phoneCode: String = ""

    fun selectCountry() {
        _nextFragment.value = Event(CountryFragment.newInstance(selectedCountry))
    }

    fun next(phone: String) {
        // Проверка ввода телефона
        if (phone.isEmpty()) {
            _toastStringInt.value = Event(R.string.login_empty_phone)
            return
        }

        // Добавление кода страны
        val countryCode = country.phoneCode.replace("-", "")
        val phoneWithCountryCode = if (phone.startsWith(countryCode)) phone else countryCode + phone

        val api = LoginApi.newInstance()
        viewModelScope.launch {
            _screenState.postValue(ScreenState.LOADING)
            val loginResponse = api.verificationChannelBegin(phoneWithCountryCode)
            _screenState.postValue(ScreenState.DEFAULT)
            if (loginResponse.status == GlobalResponseStatus.SUCCESS)
                _nextFragment.postValue(
                    Event(
                        ConfirmFragment.newInstance(
                            selectedCountry,
                            phoneWithCountryCode,
                            onLoginFlowFinishListener
                        )
                    )
                )
            else
                _screenState.postValue(ScreenState.ERROR)
            _toastText.postValue(Event(loginResponse.errorMessage))
        }
    }

    fun checkPhone(phone: String) {
        // Начальные цифры номера
        val startLength = country.phoneStart?.length ?: 0

        // Если ввели количество цифр равное начальным цифрам шаблона
        if (startLength > 0 && phone.length == startLength) {
            // Если шаблон не совпадает
            if (!phone.startsWith(country.phoneStart!!)) {
                _phoneText.value = ""
                phoneCode = country.phoneStart
                _toastStringInt.value = Event(R.string.login_error_start_phone)
            }
        }
    }
}