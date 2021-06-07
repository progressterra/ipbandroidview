package com.progressterra.ipbandroidview.ui.login.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.progressterra.ipbandroidview.ui.login.LoginSettings
import com.progressterra.ipbandroidview.ui.login.OnLoginFlowFinishListener

internal class LoginViewModelFactory(
    private val selectedCountry: String,
    private val onLoginFlowFinishListener: OnLoginFlowFinishListener?,
    private val loginSettings: LoginSettings
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        LoginViewModel(selectedCountry, onLoginFlowFinishListener, loginSettings) as T
}