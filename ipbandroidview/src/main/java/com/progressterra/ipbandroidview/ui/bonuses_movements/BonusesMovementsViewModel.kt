package com.progressterra.ipbandroidview.ui.bonuses_movements

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.BonusesApi
import com.progressterra.ipbandroidapi.utils.extentions.orIfNull
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.ui.base.BaseViewModel
import com.progressterra.ipbandroidview.ui.bonuses_movements.adapter.Transaction
import com.progressterra.ipbandroidview.ui.bonuses_movements.adapter.TransactionWithDate
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


internal class BonusesMovementsViewModel : BaseViewModel() {

    private val bonusesApi = BonusesApi.getInstance()

    private val _transactionList = MutableLiveData<List<TransactionWithDate>>()
    val transactionList: LiveData<List<TransactionWithDate>> = _transactionList

    private val _showError = MutableLiveData<Event<Int>>()
    val showError: LiveData<Event<Int>> = _showError


    init {
        getInfo()
    }

    fun getInfo(swipe: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (!swipe)
                    _screenState.postValue(ScreenState.LOADING)
                updateInfo()
                _screenState.postValue(ScreenState.DEFAULT)
            } catch (e: Exception) {
                _screenState.postValue(ScreenState.ERROR)
                _showError.postValue(Event(R.string.network_error))
            }
        }
    }

    private suspend fun updateInfo() {
        val token = getToken()
        val response = bonusesApi.getTransactionsRaw(token)
        val rawData = response.responseBody.orIfNull { throw Exception(response.errorString) }
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val data = rawData.map {
            Transaction(
                formattedDate = sdf.format(it.dateEvent),
                quantity = it.quantity,
                typeBonusName = it.typeBonusName,
                typeOperation = it.typeOperation,
                rawDate = it.dateEvent
            )
        }

        val result = TransactionWithDate.convertToTransactionsWithDate(data)

        _transactionList.postValue(result)
    }

    private suspend fun getToken(): String {
        val response = bonusesApi.getAccessToken()
        return response.responseBody?.accessToken.orIfNull { throw Exception(response.errorString) }
    }
}