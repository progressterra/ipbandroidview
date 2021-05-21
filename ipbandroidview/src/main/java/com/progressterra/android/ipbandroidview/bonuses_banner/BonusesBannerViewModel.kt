package com.progressterra.android.ipbandroidview.bonuses_banner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidapi.bonuses_repository.BonusesInfoRepositoryImpl
import com.progressterra.ipbandroidapi.repository.models.base.GlobalResponseStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BonusesBannerViewModel : ViewModel() {

    val bonusesInfo = MutableLiveData<BonusesInfo>()
    val bonusesCountIsNonZero = MutableLiveData(false)
    val repository = BonusesInfoRepositoryImpl()

    init {
        updateBonusesInfo()
    }

    fun updateBonusesInfo() {
        CoroutineScope(Job()).launch {
            getAccessToken()?.let {
                getGeneralBonusesInfo(it)
            }
        }
    }

    private suspend fun getGeneralBonusesInfo(accessToken: String) {
        repository.getBonusesInfo(accessToken).let { generalInfoResponse ->
            if (generalInfoResponse.globalResponseStatus == GlobalResponseStatus.SUCCESS) {
                with(bonusesCountIsNonZero) { postValue(generalInfoResponse.responseBody?.data?.forBurningQuantity != 0.0) }
                bonusesInfo.postValue(
                    GeneralInfoResponseConverter.convert(
                        generalInfoResponse.responseBody?.data
                    )
                )
            }
        }
    }

    private suspend fun getAccessToken(): String? {
        repository.getAccessToken().let {
            if (it.globalResponseStatus == GlobalResponseStatus.SUCCESS) {
                return it.responseBody?.accessToken
            }
        }
        return null
    }

}