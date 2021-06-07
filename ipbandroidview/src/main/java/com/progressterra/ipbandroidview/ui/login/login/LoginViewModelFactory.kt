package com.progressterra.ipbandroidview.ui.login.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.progressterra.ipbandroidview.ui.login.OnLoginFlowFinishListener
import com.progressterra.ipbandroidview.ui.login.settings.LoginFlowSettings

internal class LoginViewModelFactory(
    private val selectedCountry: String,
    private val onLoginFlowFinishListener: OnLoginFlowFinishListener?,
    private val loginFlowSettings: LoginFlowSettings
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        LoginViewModel(selectedCountry, onLoginFlowFinishListener, loginFlowSettings) as T
}