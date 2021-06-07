package com.progressterra.ipbandroidview.ui.login.confirm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidapi.interfaces.client.login.LoginApi
import com.progressterra.ipbandroidapi.remoteData.models.base.GlobalResponseStatus.ERROR
import com.progressterra.ipbandroidapi.remoteData.models.base.GlobalResponseStatus.SUCCESS
import com.progressterra.ipbandroidview.MainNavGraphDirections
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.ui.base.BaseViewModel
import com.progressterra.ipbandroidview.ui.login.settings.LoginFlowSettings
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ScreenState
import com.progressterra.ipbandroidview.utils.ToastBundle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class ConfirmViewModel(
    private val phoneNumber: String,
    private val loginFlowSettings: LoginFlowSettings
) : BaseViewModel() {
    private var isCalled: Boolean = false

    val outLinedCircles: Boolean = loginFlowSettings.confirmCodeSettings.outlinedCircles

    private val _clearConfirmCode = MutableLiveData<Event<Any>>()
    val clearConfirmCode: LiveData<Event<Any>> = _clearConfirmCode

    private val _counterScore = MutableLiveData<String>()
    val counterScore: LiveData<String> = _counterScore

    private val _resendCodeOperationReady = MutableLiveData(false)
    var resendCodeOperationReady = _resendCodeOperationReady

    private val _confirmInfo =
        MutableLiveData<String>("На указанный номер $phoneNumber было отправлено SMS с кодом. Чтобы завершить подтверждение номера, введите 4-значный код активации.")
    val confirmInfo: LiveData<String> = _confirmInfo

    init {
        startResendCodeCounter()
    }

    private fun startResendCodeCounter() {
        viewModelScope.launch {
            for (i in 59 downTo 0) {
                val toView = if (i >= 10) "$i" else "0$i"
                _counterScore.postValue(toView)
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
                _toastBundle.postValue(Event(ToastBundle(R.string.confirm_resend_error)))
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
                                true -> _action.postValue(
                                    Event(
                                        MainNavGraphDirections.actionGlobalBaseFlow(
                                            authFinished = true
                                        )
                                    )
                                )
                                false -> _action.postValue(
                                    Event(
                                        ConfirmFragmentDirections.actionConfirmFragmentToPersonalFragment(
                                            loginFlowSettings
                                        )
                                    )
                                )
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