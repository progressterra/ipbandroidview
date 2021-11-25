package com.progressterra.ipbandroidview.ui.login.personal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.progressterra.ipbandroidview.ui.login.settings.PersonalSettings

internal class PersonalViewModelFactory(
    private val personalSettings: PersonalSettings,
    private val newLoginFlow: Boolean
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        PersonalViewModel(personalSettings, newLoginFlow) as T
}