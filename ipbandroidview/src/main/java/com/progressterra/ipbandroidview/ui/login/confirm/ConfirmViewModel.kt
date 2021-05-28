package com.progressterra.ipbandroidview.ui.login.confirm

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidapi.interfaces.client.login.LoginApi
import com.progressterra.ipbandroidapi.remoteData.models.base.GlobalResponseStatus.ERROR
import com.progressterra.ipbandroidapi.remoteData.models.base.GlobalResponseStatus.SUCCESS
import com.progressterra.ipbandroidview.ui.login.personal.PersonalFragment
import com.progressterra.ipbandroidview.utils.Event
import kotlinx.coroutines.launch

class ConfirmViewModel(private val selectedCountry: String, private val phoneNumber: String) :
    ViewModel() {

    private var isCalled: Boolean = false

    private val _fragment = MutableLiveData<Event<Fragment>>()
    val fragment: LiveData<Event<Fragment>> = _fragment

    init {

    }

    fun checkIt(code: String) {
        if (code.length == 4)
            if (!isCalled) {
                isCalled = true
                viewModelScope.launch {
                    val api = LoginApi.newInstance()
                    val response = api.verificationChannelEnd(phoneNumber, code)
                    Log.d("myTag", response.status.toString())
                    Log.d("myTag", response.userExist.toString())
                    when (response.status) {
                        SUCCESS -> {
                            when (response.userExist) {
                                true -> TODO()
                                false -> _fragment.value = Event(PersonalFragment.newInstance())
                            }
                        }
                        ERROR -> TODO()
                    }
                }
            }
    }
}