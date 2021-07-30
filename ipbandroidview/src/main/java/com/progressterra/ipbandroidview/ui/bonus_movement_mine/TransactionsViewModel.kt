package com.progressterra.ipbandroidview.ui.bonus_movement_mine

import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.BonusesApi
import com.progressterra.ipbandroidapi.utils.extentions.orIfNull
import com.progressterra.ipbandroidapi.utils.extentions.parseToDate
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.ui.base.BaseViewModel
import com.progressterra.ipbandroidview.ui.bonus_movement_mine.adapter.Transaction
import com.progressterra.ipbandroidview.ui.bonus_movement_mine.adapter.TransactionWithDate
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class TransactionsViewModel(
//    private val transactionsInteractorImpl: TransactionsInteractor
) : BaseViewModel() {

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
        val response = bonusesApi.getTransactionsList(token)
        val rawData = response.responseBody.orIfNull { throw Exception(response.errorString) }
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val data = rawData.map {
            Transaction(
                formattedDate = sdf.format(it.dateEvent.parseToDate()),
                quantity = it.quantity,
                typeBonusName = it.typeBonusName,
                typeOperation = it.typeOperation,
                rawDate = it.dateEvent.parseToDate()
            )
        }.map {
            TransactionWithDate(
                transaction = it,
                dateType = dateType(it.rawDate ?: Date(), it.formattedDate)
            )
        }

        _transactionList.postValue(data)
    }

    private fun dateType(date: Date, formatted: String): String {
        return if (DateUtils.isToday(date.time)) formatted else "Сегодня"
    }

    private suspend fun getToken(): String {
        val response = bonusesApi.getAccessToken()
        return response.responseBody?.accessToken.orIfNull { throw Exception(response.errorString) }
    }
}