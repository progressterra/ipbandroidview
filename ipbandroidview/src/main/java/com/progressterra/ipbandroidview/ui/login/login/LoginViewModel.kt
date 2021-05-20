package com.progressterra.ipbandroidview.ui.login.login

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.ui.login.country.enums.Country

internal class LoginViewModel(var selectedCountry: Country) : ViewModel() {
    private val _nextFragment = MutableLiveData<Fragment>(null)
    val nextFragment: LiveData<Fragment> = _nextFragment


    fun selectCountry() {
//        _nextFragment.value = CountryFragment.newInstance(selectedCountry)
    }
}