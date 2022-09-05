package com.progressterra.ipbandroidview.ui.bonuses_movements

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidapi.api.ibonus.IBonusRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.utils.parseToDate
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


class BonusesMovementsViewModel(
    private val iBonusRepository: IBonusRepository,
    private val sCRMRepository: SCRMRepository
) : BaseViewModel() {

    private val _transactionList = MutableLiveData<List<TransactionWithDate>>()
    internal val transactionList: LiveData<List<TransactionWithDate>> = _transactionList

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
        val token = sCRMRepository.getAccessToken().getOrNull() ?: ""
        val response = iBonusRepository.getTransactionsList(token).getOrNull()
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val data = response?.map {
            Transaction(
                formattedDate = sdf.format(it.dateEvent),
                quantity = it.quantity.toInt(),
                typeBonusName = it.typeBonusName,
                typeOperation = it.typeOperation,
                rawDate = it.dateEvent.parseToDate() ?: Date(System.currentTimeMillis())
            )
        }

        val result = TransactionWithDate.convertToTransactionsWithDate(data ?: emptyList())

        _transactionList.postValue(result)
    }
}