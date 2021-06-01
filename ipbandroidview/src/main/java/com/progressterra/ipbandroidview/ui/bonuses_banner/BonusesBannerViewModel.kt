package com.progressterra.ipbandroidview.ui.bonuses_banner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.BonusesApi
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.BonusesInfo
import com.progressterra.ipbandroidapi.remoteData.models.base.GlobalResponseStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BonusesBannerViewModel : ViewModel() {

    val bonusesInfo = MutableLiveData<BonusesInfo>()
    private val repository = BonusesApi.getInstance()

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
        repository.getBonusesInfo(accessToken).let { bonusesInfoResponse ->
            if (bonusesInfoResponse.globalResponseStatus == GlobalResponseStatus.SUCCESS) {
                bonusesInfoResponse.responseBody?.let {
                    bonusesInfo.postValue(it)
                }
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