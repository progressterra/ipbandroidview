package com.progressterra.ipbandroidview.ui.login.confirm

import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
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
import com.progressterra.ipbandroidview.ui.login.settings.LoginKeys
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ScreenState
import com.progressterra.ipbandroidview.utils.ToastBundle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class ConfirmViewModel(
    private val phoneNumber: String,
    private val loginFlowSettings: LoginFlowSettings,
    private val newLoginFlowSettings: Boolean
) : BaseViewModel() {
    private var isCalled: Boolean = false

    val outLinedCircles: Boolean = loginFlowSettings.confirmCodeSettings.outlinedCircles

    private val _clearConfirmCode = MutableLiveData<Event<Any>>()
    val clearConfirmCode: LiveData<Event<Any>> = _clearConfirmCode

    private val _counterScore = MutableLiveData<Int>()
    val counterScore: LiveData<Int> = _counterScore

    private val _resendCodeOperationReady = MutableLiveData(false)
    var resendCodeOperationReady = _resendCodeOperationReady

    private val _confirmInfo =
        MutableLiveData<String>("На указанный номер $phoneNumber было отправлено SMS с кодом. Чтобы завершить подтверждение номера, введите 4-значный код активации.")
    val confirmInfo: LiveData<String> = _confirmInfo

    private val _setFragmentResult = MutableLiveData<Event<Bundle>>()
    val setFragmentResult: LiveData<Event<Bundle>> = _setFragmentResult

    private val _popBackStack = MutableLiveData<Event<Boolean>>()
    val popBackStack: LiveData<Event<Boolean>> = _popBackStack

    init {
        startResendCodeCounter()
    }

    private fun startResendCodeCounter() {
        viewModelScope.launch {
            for (i in 59 downTo 0) {
                _counterScore.postValue(i)
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
                                true -> {
                                    _setFragmentResult.postValue(Event(bundleOf(LoginKeys.AUTH_DONE to true)))
                                    if (newLoginFlowSettings)
                                        _popBackStack.postValue(Event(true))
                                    else
                                        _action.postValue(Event(MainNavGraphDirections.actionGlobalBaseFlow()))
                                }
                                false -> {
                                    Log.d("test2","Client not exists")
                                    _setFragmentResult.postValue(Event(bundleOf(LoginKeys.USER_NOT_EXIST to true)))
                                    _popBackStack.postValue(Event(true))

                                    /*if (loginFlowSettings.needSkipPersonalInfoInput) {
                                        _setFragmentResult.postValue(Event(bundleOf(LoginKeys.USER_NOT_EXIST to true)))
                                    } else {
                                        _action.postValue(
                                            Event(
                                                ConfirmFragmentDirections.actionConfirmFragmentToPersonalFragment(
                                                    loginFlowSettings
                                                )
                                            )
                                        )
                                    }*/

                                }
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