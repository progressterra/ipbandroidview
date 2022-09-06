package com.progressterra.ipbandroidview.ui.login.login

import androidx.lifecycle.*
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.ui.base.BaseViewModel
import com.progressterra.ipbandroidview.ui.login.country.enums.Country
import com.progressterra.ipbandroidview.ui.login.settings.LoginFlowSettings
import com.progressterra.ipbandroidview.usecases.scrm.StartSMSAuthUseCase
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ScreenState
import com.progressterra.ipbandroidview.utils.ToastBundle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    selectedCountry: String,
    private var loginFlowSettings: LoginFlowSettings,
    private val startSMSAuthUseCase: StartSMSAuthUseCase,
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
        if (phone.isEmpty()) {
            _toastBundle.value = Event(ToastBundle(R.string.login_empty_phone))
            return
        }
        val countryCode = country.phoneCode.replace("-", "")
        val phoneWithCountryCode = if (phone.startsWith(countryCode)) phone else countryCode + phone
        viewModelScope.launch(Dispatchers.IO) {
            _screenState.postValue(ScreenState.LOADING)
            if (startSMSAuthUseCase.startAuth(phone).isSuccess) {
                _action.postValue(
                    Event(
                        LoginFragmentDirections.actionFragmentLoginToConfirmFragment(
                            phoneWithCountryCode,
                            loginFlowSettings
                        )
                    )
                )
            } else {
                _screenState.postValue(ScreenState.ERROR)
            }
        }
    }

    fun checkPhone(phone: String) {
        val startLength = country.phoneStart?.length ?: 0
        if (startLength > 0 && phone.length == startLength) {
            if (!phone.startsWith(country.phoneStart!!)) {
                _phoneText.value = ""
                phoneCode = country.phoneStart!!
                _toastBundle.value = Event(ToastBundle(R.string.login_error_start_phone, phoneCode))
            }
        }
    }
}