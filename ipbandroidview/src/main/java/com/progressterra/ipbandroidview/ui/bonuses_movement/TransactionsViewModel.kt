package com.progressterra.ipbandroidview.ui.bonuses_movement

//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.progressterra.android.ipharm.data.services.Status
//import com.progressterra.ipbandroidview.ui.bonus_movement_mine.adapter.TransactionWithDate
//import com.progressterra.ipbandroidapi.interfaces.client.bonuses.models.BonusesInfo
//import com.progressterra.ipbandroidview.R
//import com.progressterra.ipbandroidview.utils.Event
//import com.progressterra.ipbandroidview.utils.ScreenState
//import kotlinx.coroutines.CoroutineExceptionHandler
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//
//class TransactionsViewModel @Inject constructor(var transactionsInteractorImpl: TransactionsInteractor) :
//    ViewModel() {
//
//
//    private val _screenState = MutableLiveData(ScreenState.LOADING)
//    val screenState: LiveData<ScreenState> = _screenState
//
//    private val _transactionList = MutableLiveData<List<TransactionWithDate>>()
//    val transactionList: LiveData<List<TransactionWithDate>> = _transactionList
//
//    private val _bonusesInfo = MutableLiveData<BonusesInfo>()
//    val bonusesInfo: LiveData<BonusesInfo> = _bonusesInfo
//
//    private val _showError = MutableLiveData<Event<Int>>()
//    val showError: LiveData<Event<Int>> = _showError
//
//
//    init {
//        getInfo()
//    }
//
//    fun getInfo(actionFromSwipeRefresh: Boolean = false) {
//
//        if (!actionFromSwipeRefresh) {
//            _screenState.postValue(ScreenState.LOADING)
//        }
//
//        viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
//            _screenState.postValue(
//                ScreenState.ERROR
//            )
//            _showError.postValue(Event(R.string.network_error))
//        }) {
//            var accessToken: String
//            transactionsInteractorImpl.getAccessToken().let {
//                if (it.status == Status.SUCCESS) {
//                    accessToken = it.data!!
//                } else {
//                    _screenState.postValue(ScreenState.ERROR)
//                    _showError.postValue(Event(R.string.network_error))
//                    return@launch
//                }
//            }
//
//            transactionsInteractorImpl.getGeneralBonusesInfo(accessToken).let {
//                if (it.status == Status.SUCCESS) {
//                    _bonusesInfo.postValue(it.data!!)
//                } else {
//                    _screenState.postValue(ScreenState.ERROR)
//                    _showError.postValue(Event(R.string.network_error))
//                    return@launch
//                }
//            }
//
//            transactionsInteractorImpl.getTransactionsList(accessToken).let {
//                if (it.status == Status.SUCCESS) {
//
//                    val convertedTranslations =
//                        TransactionWithDate.convertToTransactionsWithDate(it.data!!)
//
//                    _transactionList.postValue(convertedTranslations)
//                } else {
//                    _screenState.postValue(ScreenState.ERROR)
//                    _showError.postValue(Event(R.string.network_error))
//                    return@launch
//                }
//            }
//
//            _screenState.postValue(ScreenState.DEFAULT)
//        }
//    }
//
//}