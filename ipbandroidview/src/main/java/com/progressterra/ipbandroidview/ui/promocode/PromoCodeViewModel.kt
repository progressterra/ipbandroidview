package com.progressterra.ipbandroidview.ui.promocode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.data.CommonRepository
import com.progressterra.ipbandroidview.data.IRepository
import com.progressterra.ipbandroidview.ui.base.BaseBindingViewModel
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.loadingResult
import com.progressterra.ipbandroidview.utils.extensions.toSuccessResult
import com.progressterra.ipbandroidview.utils.extensions.toToastResult

class PromoCodeViewModel : BaseBindingViewModel() {
    private val repo: IRepository.PromoCode = CommonRepository()

    val code = MutableLiveData("")

    val buttonEnabled: LiveData<Boolean> = code.map { it.isNotBlank() }

    private val _result = MutableLiveData<SResult<*>>(toSuccessResult())
    val result: LiveData<SResult<*>> = _result

    fun send() {
        val mCode = code.value ?: return
        if (mCode.isNotBlank()) {
            safeNamedLaunch(launchName = javaClass.simpleName,
                onCatch = {
                    _result.postValue(R.string.ErrorString.toToastResult())
                }) {
                _result.postValue(loadingResult())
                val tokenRes = repo.getAccessToken()
                if (tokenRes is SResult.Failed) {
                    _result.postValue(R.string.ErrorString.toToastResult())
                } else if (tokenRes is SResult.Success) {
                    when (val result = repo.setPromoCode(tokenRes.data, mCode)) {
                        is SResult.Completed -> _result.postValue(R.string.promocode_success.toToastResult())
                        is SResult.Failed -> _result.postValue(result)
                        else -> _result.postValue(R.string.ErrorString.toToastResult())
                    }
                }
            }
        }
    }
}