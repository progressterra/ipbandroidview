package com.progressterra.ipbandroidview.ui.login.personal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.progressterra.ipbandroidview.ui.login.OnLoginFlowFinishListener

internal class PersonalViewModelFactory(private val onLoginFlowFinishListener: OnLoginFlowFinishListener?) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        PersonalViewModel(onLoginFlowFinishListener) as T
}