package com.progressterra.android.ipbandroidview.bonuses_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.BonusesApi
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.BonusesInfo
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.TransactionList
import com.progressterra.ipbandroidapi.remoteData.models.base.GlobalResponseStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BonusesDetailsViewModel : ViewModel() {

    val bonusesInfo = MutableLiveData<BonusesInfo>()
    val transactionList = MutableLiveData<TransactionList>()
    private val repository = BonusesApi.getInstance()


    fun updateBonusesInfo() {
        CoroutineScope(Job()).launch {
            getAccessToken()?.let {
                getGeneralBonusesInfo(it)
                var b = getTransactionsList(it)
            }
        }
    }

    suspend fun getTransactionsList(accessToken: String) {
        repository.getTransactionsList(accessToken).let {
            transactionList.postValue(it.responseBody!!)
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