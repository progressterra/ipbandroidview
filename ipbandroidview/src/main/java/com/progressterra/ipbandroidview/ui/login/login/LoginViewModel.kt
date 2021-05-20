package com.progressterra.ipbandroidview.ui.login.login

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.progressterra.ipbandroidview.ui.login.country.CountryFragment
import com.progressterra.ipbandroidview.ui.login.country.enums.Country

internal class LoginViewModel(var selectedCountry: String) : ViewModel() {
    private val _nextFragment = MutableLiveData<Fragment>()
    val nextFragment: LiveData<Fragment> = _nextFragment

    private val _countryText = MutableLiveData<Country>(Country.valueOf(selectedCountry))
    val countryText: LiveData<String> = _countryText.map {
        "${it.titleRu} (+ ${it.phoneCode})"
    }


    fun selectCountry() {
        _nextFragment.value = CountryFragment.newInstance(selectedCountry)
    }
}