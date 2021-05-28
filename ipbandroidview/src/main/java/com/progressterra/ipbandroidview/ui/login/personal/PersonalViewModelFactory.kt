package com.progressterra.ipbandroidview.ui.login.personal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

internal class PersonalViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        PersonalViewModel() as T
}