package com.progressterra.ipbandroidview.ui.bonuses_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidapi.api.purchases.PurchasesRepository
import com.progressterra.ipbandroidview.data.model.BonusMessage
import com.progressterra.ipbandroidview.data.model.BonusesInfo
import com.progressterra.ipbandroidview.data.model.Purchase
import com.progressterra.ipbandroidview.data.model.Transaction
import com.progressterra.ipbandroidview.ui.base.BaseBindingViewModel
import com.progressterra.ipbandroidview.utils.ScreenState
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class BonusesDetailsViewModel(
    private val purchasesRepository: PurchasesRepository
) : BaseBindingViewModel() {


    private val _bonusesInfo = MutableLiveData<BonusesInfo>()
    val bonusesInfo: LiveData<BonusesInfo> = _bonusesInfo

    private val _transactionList = MutableLiveData<List<Transaction>>()
    val transactionList: LiveData<List<Transaction>> = _transactionList

    private val _purchasesList = MutableLiveData<List<Purchase>>()
    val purchasesList: LiveData<List<Purchase>> = _purchasesList

    private val _bonusMessageList = MutableLiveData<List<BonusMessage>>()
    val bonusMessageList: LiveData<List<BonusMessage>> = _bonusMessageList


    private val _status = MutableLiveData(ScreenState.LOADING)
    val status: LiveData<ScreenState> = _status

    init {
        updateDetailBonusesInfo()
    }

    fun updateDetailBonusesInfo() {
        viewModelScope.launch {
            getAccessToken()?.let {
                _status.postValue(ScreenState.LOADING)

                val generalInfoResponse = async {
                    getGeneralBonusesInfo(it)
                }

                val transactionListResponse = async {
                    getTransactionsList(it)
                }

                val purchaseListResponse = async {
                    getPurchasesList(it)
                }

                val bonusMessageListResponse = async {
                    getBonusMessageList(it)
                }
                generalInfoResponse.await()
                transactionListResponse.await()
                purchaseListResponse.await()
                bonusMessageListResponse.await()

                _status.postValue(ScreenState.DEFAULT)
            }
        }
    }

    private suspend fun getBonusMessageList(accessToken: String) {
//        repository.getBonusMessageList(accessToken).let { bonusMessageListResponse ->
//            if (bonusMessageListResponse.globalResponseStatus == GlobalResponseStatus.SUCCESS) {
//                bonusMessageListResponse.responseBody?.let {
//                    _bonusMessageList.postValue(it)
//                }
//            } else {
//                _status.postValue(ScreenState.ERROR)
//            }
//        }
    }


    private suspend fun getPurchasesList(accessToken: String) {
//        purchasesRepository.getPurchasesList(accessToken).let { purchasesListResponse ->
//            if (purchasesListResponse.globalResponseStatus == GlobalResponseStatus.SUCCESS) {
//                purchasesListResponse.responseBody?.let {
//                    _purchasesList.postValue(it)
//                }
//            } else {
//                _status.postValue(ScreenState.ERROR)
//            }
//        }
    }

    private suspend fun getTransactionsList(accessToken: String) {
//        repository.getTransactionsList(accessToken).let { transactionsListResponse ->
//            if (transactionsListResponse.globalResponseStatus == GlobalResponseStatus.SUCCESS) {
//                transactionsListResponse.responseBody?.let {
//                    _transactionList.postValue(it)
//                }
//            } else {
//                _status.postValue(ScreenState.ERROR)
//            }
//        }
    }

    private suspend fun getGeneralBonusesInfo(accessToken: String) {
//        repository.getBonusesInfo(accessToken).let { bonusesInfoResponse ->
//            if (bonusesInfoResponse.globalResponseStatus == GlobalResponseStatus.SUCCESS) {
//                bonusesInfoResponse.responseBody?.let {
//                    _bonusesInfo.postValue(it)
//                }
//            } else {
//                _status.postValue(ScreenState.ERROR)
//            }
//        }
    }

    private suspend fun getAccessToken(): String? {
//        repository.getAccessToken().let {
//            if (it.globalResponseStatus == GlobalResponseStatus.SUCCESS) {
//                return it.responseBody?.accessToken
//            } else {
//                _status.postValue(ScreenState.ERROR)
//            }
//        }
//        return null
        return null
    }
}