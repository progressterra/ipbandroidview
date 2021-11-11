package com.progressterra.ipbandroidview.ui.login.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.progressterra.ipbandroidview.ui.login.settings.LoginFlowSettings

internal class CountryViewModelFactory(
    private val loginFlowSettings: LoginFlowSettings
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        CountryViewModel(loginFlowSettings) as T
}