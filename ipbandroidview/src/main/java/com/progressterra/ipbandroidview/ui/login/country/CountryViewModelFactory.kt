package com.progressterra.ipbandroidview.ui.login.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.progressterra.ipbandroidview.ui.login.LoginSettings

internal class CountryViewModelFactory(
    private val selectedCountry: String,
    private val loginSettings: LoginSettings
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        CountryViewModel(selectedCountry, loginSettings) as T
}