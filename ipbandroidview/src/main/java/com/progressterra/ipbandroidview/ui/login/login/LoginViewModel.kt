package com.progressterra.ipbandroidview.ui.login.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidapi.interfaces.client.login.LoginApi
import com.progressterra.ipbandroidapi.remoteData.models.base.GlobalResponseStatus
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.ui.base.BaseViewModel
import com.progressterra.ipbandroidview.ui.login.country.enums.Country
import com.progressterra.ipbandroidview.ui.login.settings.LoginFlowSettings
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ScreenState
import com.progressterra.ipbandroidview.utils.ToastBundle
import kotlinx.coroutines.launch

internal class LoginViewModel(
    selectedCountry: String,
    private var loginFlowSettings: LoginFlowSettings
) : BaseViewModel() {

    private var country: Country = Country.valueOf(selectedCountry)

    private val _countryText = MutableLiveData(country)
    val countryText: LiveData<String> = _countryText.map {
        "${it.titleRu} (+ ${it.phoneCode})"
    }

    private val _phoneText = MutableLiveData("")
    val phoneText: LiveData<String> = _phoneText

    private var phoneCode: String = ""

    fun selectCountry() {
        _action.value =
            Event(
                LoginFragmentDirections.actionFragmentLoginToFragmentCountry(loginFlowSettings)
            )
    }

    fun updateCountry(newCountry: String) {
        loginFlowSettings = loginFlowSettings.copy(
            phoneNumberSettings = loginFlowSettings.phoneNumberSettings.copy(defaultCountry = newCountry)
        )
        country = Country.valueOf(newCountry)
        _countryText.value = country
    }

    fun next(phone: String) {
        // Проверка ввода телефона
        if (phone.isEmpty()) {
            _toastBundle.value = Event(ToastBundle(R.string.login_empty_phone))
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
                _action.postValue(
                    Event(
                        LoginFragmentDirections.actionFragmentLoginToConfirmFragment(
                            phoneWithCountryCode,
                            loginFlowSettings
                        )
                    )
                )
            else {
                _screenState.postValue(ScreenState.ERROR)
                _toastBundle.postValue(Event(ToastBundle(id = null, loginResponse.errorMessage)))
            }
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
                phoneCode = country.phoneStart as String
                _toastBundle.value = Event(ToastBundle(R.string.login_error_start_phone, phoneCode))
            }
        }
    }
}