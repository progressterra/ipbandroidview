package com.progressterra.android.ipbandroidview.bonuses_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.BonusesInfo

class BonusesDetailsViewModel : ViewModel() {
    val bonusesInfo = MutableLiveData<BonusesInfo>()
    val transactionList = MutableLiveData<List<TransactionResponse>>()
    val orderList = MutableLiveData<List<Purchase>>()


    fun getAllData() {}
}