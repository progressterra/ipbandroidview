package com.progressterra.ipbandroidview.ui.login.confirm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

internal class ConfirmViewModelFactory(
    private val selectedCountry: String,
    private val phoneNumber: String
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ConfirmViewModel(selectedCountry, phoneNumber) as T
}