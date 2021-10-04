package com.progressterra.ipbandroidview.ui.remove_account

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.*
import com.progressterra.ipbandroidapi.api.scrmApiQwerty.SCRMApiQwerty
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.BonusesApi
import com.progressterra.ipbandroidapi.localdata.shared_pref.UserData
import com.progressterra.ipbandroidapi.utils.extentions.orIfNull
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.ui.base.BaseViewModel
import com.progressterra.ipbandroidview.ui.login.settings.ConfirmCodeSettings
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ScreenState
import com.progressterra.ipbandroidview.utils.extensions.isSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RemoveAccountViewModel(confirmCodeSettings: ConfirmCodeSettings) : BaseViewModel() {
    private var isCalled: Boolean = false
    private val api: SCRMApiQwerty.ClientManagement = SCRMApiQwerty.ClientManagement()
    private val bonusesApi: BonusesApi = BonusesApi.getInstance()

    val outLinedCircles: Boolean = confirmCodeSettings.outlinedCircles

    private val _clearConfirmCode = MutableLiveData<Event<Any>>()
    val clearConfirmCode: LiveData<Event<Any>> = _clearConfirmCode

    private val _counterScore = MutableLiveData<Int>()
    val counterScore: LiveData<Int> = _counterScore

    private val _resendCodeOperationReady = MutableLiveData(true)
    val resendCodeOperationReady: LiveData<Boolean> = _resendCodeOperationReady

    private val _confirmInfo =
        MutableLiveData("На номер ${UserData.phone} было отправлено SMS с кодом. Чтобы завершить удаление аккаунта, введите 4-значный код активации.")
    val confirmInfo: LiveData<String> = _confirmInfo

    private val _setFragmentResult = MutableLiveData<Event<Bundle>>()
    val setFragmentResult: LiveData<Event<Bundle>> = _setFragmentResult

    private val _popBackStack = MutableLiveData<Event<Boolean>>()
    val popBackStack: LiveData<Event<Boolean>> = _popBackStack

    init {
        startResendCodeCounter()
        resendConfirmationCode()
    }

    private fun startResendCodeCounter() {
        viewModelScope.launch {
            for (i in 59 downTo 0) {
                _counterScore.postValue(i)
                delay(1000)
            }
            _resendCodeOperationReady.postValue(true)
        }
    }

    fun resendConfirmationCode() {

        if (resendCodeOperationReady.value == false) {
            return
        }


        safeLaunch {
            val tokenResponse = bonusesApi.getAccessToken()
            val token = tokenResponse.responseBody?.accessToken.orIfNull {
                showToast(tokenResponse.errorString)
                return@safeLaunch
            }

            val response = api.removeClientBegin(token)
            if (response.isSuccess()) {
                _resendCodeOperationReady.postValue(false)
                startResendCodeCounter()
            } else {
                showToast(R.string.remove_resend_error)
            }
        }
    }

    fun checkIt(code: String) {
        if (code.length == 4)
            if (!isCalled) {
                isCalled = true
                safeLaunch {
                    _screenState.postValue(ScreenState.LOADING)

                    val tokenResponse = bonusesApi.getAccessToken()
                    val token = tokenResponse.responseBody?.accessToken.orIfNull {
                        showToast(tokenResponse.errorString)
                        _screenState.postValue(ScreenState.DEFAULT)
                        return@safeLaunch
                    }

                    val response = api.removeClientEnd(token, code)

                    if (response.isSuccess()) {
                        _setFragmentResult.postValue(Event(bundleOf(RemoveAccountKeys.SUCCESS_KEY to true)))
                        _popBackStack.postValue(Event(true))
                    } else {
                        _clearConfirmCode.postValue(Event(Any()))
                        isCalled = false
                        _confirmInfo.postValue("Код введен неправльно. Проверьте еще раз SMS с кодом. Или можете запросить новый код, нажав “Выслать повторно”")
                    }
                }
            }
    }
}

internal class RemoveAccountViewModelFactory(
    private val confirmCodeSettings: ConfirmCodeSettings
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        RemoveAccountViewModel(confirmCodeSettings) as T
}