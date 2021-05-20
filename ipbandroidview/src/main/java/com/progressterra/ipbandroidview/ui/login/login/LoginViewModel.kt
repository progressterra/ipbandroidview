package com.progressterra.ipbandroidview.ui.login.login

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.ui.login.country.CountryFragment
import com.progressterra.ipbandroidview.ui.login.country.enums.Country

internal class LoginViewModel(var selectedCountry: String) : ViewModel() {

    private val country: Country = Country.valueOf(selectedCountry)

    private val _nextFragment = MutableLiveData<Fragment>()
    val nextFragment: LiveData<Fragment> = _nextFragment

    private val _countryText = MutableLiveData(country)
    val countryText: LiveData<String> = _countryText.map {
        "${it.titleRu} (+ ${it.phoneCode})"
    }

    private val _phoneText = MutableLiveData("")
    val phoneText: LiveData<String> = _phoneText

    private val _toastString = MutableLiveData<Int>()
    val toastString: LiveData<Int> = _toastString

    var phoneCode: String = ""

    fun selectCountry() {
        _nextFragment.value = CountryFragment.newInstance(selectedCountry)
    }

    fun next(phone: String) {
        // Проверка ввода телефона
        if (phone.isEmpty()) {
            _toastString.value = R.string.login_empty_phone
            return
        }

        // Добавление кода страны
        val countryCode = country.phoneCode.replace("-", "")
        val phoneWithCountryCode = if (phone.startsWith(countryCode)) phone else countryCode + phone
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
                _toastString.value = R.string.login_error_start_phone
            }
        }
    }
}