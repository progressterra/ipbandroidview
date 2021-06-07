package com.progressterra.ipbandroidview.ui.login.confirm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.progressterra.ipbandroidview.ui.login.OnLoginFlowFinishListener

internal class ConfirmViewModelFactory(
    private val phoneNumber: String
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ConfirmViewModel(phoneNumber, false, false) as T
}