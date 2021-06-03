package com.progressterra.ipbandroidview.ui.login.confirm

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidapi.interfaces.client.login.LoginApi
import com.progressterra.ipbandroidapi.remoteData.models.base.GlobalResponseStatus.ERROR
import com.progressterra.ipbandroidapi.remoteData.models.base.GlobalResponseStatus.SUCCESS
import com.progressterra.ipbandroidview.ui.login.OnLoginFlowFinishListener
import com.progressterra.ipbandroidview.ui.login.personal.PersonalFragment
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class ConfirmViewModel(
    private val phoneNumber: String,
    private val onLoginFlowFinishListener: OnLoginFlowFinishListener?
) :
    ViewModel() {

    private var isCalled: Boolean = false

    private val _screenState = MutableLiveData(ScreenState.DEFAULT)
    val screenState: LiveData<ScreenState> = _screenState

    private val _clearConfirmCode = MutableLiveData<Event<Any>>()
    val clearConfirmCode: LiveData<Event<Any>> = _clearConfirmCode

    private val _counterScore = MutableLiveData<String>()
    val counterScore: LiveData<String> = _counterScore

    private val _resendCodeOperationReady = MutableLiveData(false)
    var resendCodeOperationReady = _resendCodeOperationReady

    private val _fragment = MutableLiveData<Event<Fragment>>()
    val fragment: LiveData<Event<Fragment>> = _fragment

    private val _confirmInfo =
        MutableLiveData<String>("На указанный номер $phoneNumber было отправлено SMS с кодом. Чтобы завершить подтверждение номера, введите 4-значный код активации.")
    val confirmInfo: LiveData<String> = _confirmInfo

    private val _toastText = MutableLiveData<Event<String>>()
    val toastText: LiveData<Event<String>> = _toastText

    init {
        startResendCodeCounter()
    }

    private fun startResendCodeCounter() {
        viewModelScope.launch {
            for (i in 59 downTo 0) {
                _counterScore.postValue(i.toString())
                delay(1000)
            }
            resendCodeOperationReady.postValue(true)
        }
    }

    fun resendConfirmationCode() {

        if (resendCodeOperationReady.value == false) {
            return
        }

        val api = LoginApi.newInstance()
        viewModelScope.launch {
            val loginResponse = api.verificationChannelBegin(phoneNumber)
            if (loginResponse.status == SUCCESS) {
                resendCodeOperationReady.postValue(false)
                startResendCodeCounter()
            } else {
                _toastText.postValue(Event("Ошибка при повторной отправке кода"))
            }
        }
    }

    fun checkIt(code: String) {
        if (code.length == 4)
            if (!isCalled) {
                isCalled = true
                viewModelScope.launch {
                    _screenState.postValue(ScreenState.LOADING)
                    val api = LoginApi.newInstance()
                    val response = api.verificationChannelEnd(phoneNumber, code)
                    _screenState.postValue(ScreenState.DEFAULT)
                    when (response.status) {
                        SUCCESS -> {
                            isCalled = false
                            when (response.userExist) {
                                true -> onLoginFlowFinishListener?.onLoginFinish()
                                false -> _fragment.value =
                                    Event(PersonalFragment.newInstance(onLoginFlowFinishListener))
                            }
                        }
                        ERROR -> {
                            _clearConfirmCode.postValue(Event(Any()))
                            isCalled = false
                            _confirmInfo.postValue("Код введен неправльно. Проверьте еще раз SMS с кодом. Или можете запросить новый код, нажав “Выслать повторно”")
                        }
                    }
                }
            }
    }
}