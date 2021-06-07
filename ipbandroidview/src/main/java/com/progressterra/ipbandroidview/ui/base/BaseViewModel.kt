package com.progressterra.ipbandroidview.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ScreenState
import com.progressterra.ipbandroidview.utils.ToastBundle

internal open class BaseViewModel : ViewModel() {
    internal val _action = MutableLiveData<Event<NavDirections>>()
    val action: LiveData<Event<NavDirections>> = _action

    internal val _toastBundle = MutableLiveData<Event<ToastBundle>>()
    val toastBundle: LiveData<Event<ToastBundle>> = _toastBundle

    internal val _screenState = MutableLiveData(ScreenState.DEFAULT)
    val screenState: LiveData<ScreenState> = _screenState
}