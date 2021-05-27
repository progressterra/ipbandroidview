package com.progressterra.android.ipbandroidview.bonuses_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.progressterra.android.ipbandroidview.bonuses_details.tabs.Purchase
import com.progressterra.android.ipbandroidview.utils.ScreenState
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.BonusesApi
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.BonusesInfo
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.TransactionList
import com.progressterra.ipbandroidapi.remoteData.models.base.GlobalResponseStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

internal class BonusesDetailsViewModel : ViewModel() {

    private val repository = BonusesApi.getInstance()

    val bonusesInfo = MutableLiveData<BonusesInfo>()
    val transactionList = MutableLiveData<TransactionList>()
    val status = MutableLiveData(ScreenState.LOADING)
    val orderList = MutableLiveData<List<Purchase>>()

    fun updateDetailBonusesInfo() {
        CoroutineScope(Job()).launch {
            getAccessToken()?.let {
                status.postValue(ScreenState.LOADING)

                async {
                    getGeneralBonusesInfo(it)
                }.await()

                async {
                    getTransactionsList(it)
                }.await()

                status.postValue(ScreenState.DEFAULT)
            }
        }
    }

    private suspend fun getTransactionsList(accessToken: String) {
        repository.getTransactionsList(accessToken).let { transactionsListResponse ->
            if (transactionsListResponse.globalResponseStatus == GlobalResponseStatus.SUCCESS) {
                transactionsListResponse.responseBody?.let {
                    transactionList.postValue(it)
                }
            } else {
                status.postValue(ScreenState.ERROR)
            }
        }
    }

    private suspend fun getGeneralBonusesInfo(accessToken: String) {
        repository.getBonusesInfo(accessToken).let { bonusesInfoResponse ->
            if (bonusesInfoResponse.globalResponseStatus == GlobalResponseStatus.SUCCESS) {
                bonusesInfoResponse.responseBody?.let {
                    bonusesInfo.postValue(it)
                }
            } else {
                status.postValue(ScreenState.ERROR)
            }
        }
    }

    private suspend fun getAccessToken(): String? {
        repository.getAccessToken().let {
            if (it.globalResponseStatus == GlobalResponseStatus.SUCCESS) {
                return it.responseBody?.accessToken
            } else {
                status.postValue(ScreenState.ERROR)
            }
        }
        return null
    }

}