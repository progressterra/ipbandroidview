package com.progressterra.ipbandroidview.ui.login.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.progressterra.ipbandroidview.ui.login.country.enums.Country

internal class LoginViewModelFactory(private val selectedCountry: String) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        LoginViewModel(selectedCountry) as T
}