package com.progressterra.ipbandroidview.ui.login.confirm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.progressterra.ipbandroidview.ui.login.settings.LoginFlowSettings

internal class ConfirmViewModelFactory(
    private val phoneNumber: String,
    private val loginFlowSettings: LoginFlowSettings,
    private val newLoginFlow: Boolean
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        ConfirmViewModel(
            phoneNumber = phoneNumber,
            loginFlowSettings = loginFlowSettings,
            newLoginFlowSettings = newLoginFlow
        ) as T
}