package com.progressterra.android.ipbandroidview.bonuses_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.progressterra.android.ipbandroidview.utils.ScreenState
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.*
import com.progressterra.ipbandroidapi.remoteData.models.base.GlobalResponseStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

internal class BonusesDetailsViewModel : ViewModel() {

    private val repository = BonusesApi.getInstance()

    val bonusesInfo = MutableLiveData<BonusesInfo>()
    val transactionList = MutableLiveData<List<Transaction>>()
    val purchasesList = MutableLiveData<List<Purchase>>()
    val bonusMessageList = MutableLiveData<List<BonusMessage>>()
    val status = MutableLiveData(ScreenState.LOADING)


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

                async {
                    getPurchasesList(it)
                }.await()

                async {
                    getBonusMessageList(it)
                }.await()

                status.postValue(ScreenState.DEFAULT)
            }
        }
    }

    private suspend fun getBonusMessageList(accessToken: String) {
        repository.getBonusMessageList(accessToken).let { bonusMessageListResponse ->
            if (bonusMessageListResponse.globalResponseStatus == GlobalResponseStatus.SUCCESS) {
                bonusMessageListResponse.responseBody?.let {
                    bonusMessageList.postValue(it)
                }
            } else {
                status.postValue(ScreenState.ERROR)
            }
        }
    }


    private suspend fun getPurchasesList(accessToken: String) {
        repository.getPurchasesList(accessToken).let { purchasesListResponse ->
            if (purchasesListResponse.globalResponseStatus == GlobalResponseStatus.SUCCESS) {
                purchasesListResponse.responseBody?.let {
                    purchasesList.postValue(it)
                }
            } else {
                status.postValue(ScreenState.ERROR)
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