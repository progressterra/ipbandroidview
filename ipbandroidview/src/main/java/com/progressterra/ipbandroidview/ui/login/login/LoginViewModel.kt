package com.progressterra.ipbandroidview.ui.login.login

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.progressterra.ipbandroidapi.interfaces.client.login.LoginApi
import com.progressterra.ipbandroidapi.remoteData.models.base.GlobalResponseStatus
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.ui.login.LoginSettings
import com.progressterra.ipbandroidview.ui.login.OnLoginFlowFinishListener
import com.progressterra.ipbandroidview.ui.login.confirm.ConfirmFragment
import com.progressterra.ipbandroidview.ui.login.country.enums.Country
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ScreenState
import com.progressterra.ipbandroidview.utils.ToastBundle
import kotlinx.coroutines.launch

internal class LoginViewModel(
    private var selectedCountry: String,
    private val onLoginFlowFinishListener: OnLoginFlowFinishListener?,
    private val loginSettings: LoginSettings
) : ViewModel() {

    private val _screenState = MutableLiveData(ScreenState.DEFAULT)
    val screenState: LiveData<ScreenState> = _screenState

    private val country: Country = Country.valueOf(selectedCountry)

    private val _nextFragment = MutableLiveData<Event<Fragment>>()
    val nextFragment: LiveData<Event<Fragment>> = _nextFragment

    private val _countryText = MutableLiveData(country)
    val countryText: LiveData<String> = _countryText.map {
        "${it.titleRu} (+ ${it.phoneCode})"
    }

    private val _action = MutableLiveData<Event<NavDirections>>()
    val action: LiveData<Event<NavDirections>> = _action

    private val _phoneText = MutableLiveData("")
    val phoneText: LiveData<String> = _phoneText

    private val _toastBundle = MutableLiveData<Event<ToastBundle>>()
    val toastBundle: LiveData<Event<ToastBundle>> = _toastBundle

    private var phoneCode: String = ""

    fun selectCountry() {
        _action.value =
            Event(LoginFragmentDirections.actionFragmentLoginToFragmentCountry(selectedCountry, loginSettings))
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
                _nextFragment.postValue(
                    Event(
                        ConfirmFragment.newInstance(
                            selectedCountry,
                            phoneWithCountryCode,
                            onLoginFlowFinishListener
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
                phoneCode = country.phoneStart
                _toastBundle.value = Event(ToastBundle(R.string.login_error_start_phone, phoneCode))
            }
        }
    }
}