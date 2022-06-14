package com.progressterra.ipbandroidview.ui.login.personal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.progressterra.ipbandroidview.ui.login.settings.LoginFlowSettings

internal class PersonalViewModelFactory(
    private val loginFlowSettings: LoginFlowSettings
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        PersonalViewModel(loginFlowSettings) as T
}