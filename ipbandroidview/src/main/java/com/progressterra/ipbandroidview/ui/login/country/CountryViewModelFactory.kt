package com.progressterra.ipbandroidview.ui.login.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

internal class CountryViewModelFactory(private val selectedCountry: String) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        CountryViewModel(selectedCountry) as T
}