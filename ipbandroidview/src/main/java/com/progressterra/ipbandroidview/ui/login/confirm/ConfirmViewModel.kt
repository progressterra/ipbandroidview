package com.progressterra.ipbandroidview.ui.login.confirm

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidapi.user.UserData
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.ui.base.BaseViewModel
import com.progressterra.ipbandroidview.ui.login.settings.LoginFlowSettings
import com.progressterra.ipbandroidview.usecases.scrm.ConfirmSMSCodeUseCase
import com.progressterra.ipbandroidview.usecases.scrm.StartSMSAuthUseCase
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ScreenState
import com.progressterra.ipbandroidview.utils.ToastBundle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class ConfirmViewModel(
    private val phoneNumber: String,
    private val loginFlowSettings: LoginFlowSettings,
    private val confirmSMSCodeUseCase: ConfirmSMSCodeUseCase,
    private val startSMSAuthUseCase: StartSMSAuthUseCase
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
        MutableLiveData("На указанный номер $phoneNumber было отправлено SMS с кодом. Чтобы завершить подтверждение номера, введите 4-значный код активации.")
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

        viewModelScope.launch {
            val loginResponse =
                startSMSAuthUseCase.startAuth(phoneNumber)
            if (loginResponse) {
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
                    val response = confirmSMSCodeUseCase.confirmCode(phoneNumber, code)
                    _screenState.postValue(ScreenState.DEFAULT)
                    if (response) {
                        isCalled = false
                        UserData.clientExist = true
                        _action.postValue(
                            Event(
                                ConfirmFragmentDirections.actionConfirmFragmentToPersonalFragment(
                                    loginFlowSettings
                                )
                            )
                        )
                    } else {
                        _clearConfirmCode.postValue(Event(Any()))
                        isCalled = false
                        _confirmInfo.postValue("Код введен неправльно. Проверьте еще раз SMS с кодом. Или можете запросить новый код, нажав “Выслать повторно”")
                    }
                }
            }
    }
}