package com.progressterra.ipbandroidview.ui.addresses

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidapi.interfaces.client.addresses.AddressApi
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.BonusesApi
import com.progressterra.ipbandroidapi.remoteData.models.base.GlobalResponseStatus
import com.progressterra.ipbandroidapi.utils.extentions.format
import com.progressterra.ipbandroidapi.utils.extentions.orIfNull
import com.progressterra.ipbandroidapi.utils.extentions.orNow
import com.progressterra.ipbandroidapi.utils.extentions.parseToDate
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.ui.addresses.models.AddressUI
import com.progressterra.ipbandroidview.ui.addresses.models.AddressesMapper
import com.progressterra.ipbandroidview.ui.base.BaseViewModel
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ScreenState
import com.progressterra.ipbandroidview.utils.ToastBundle
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.util.*

class ListOfAddressesViewModel :
    BaseViewModel() {

    private val addressApi = AddressApi()
    private val repository = BonusesApi.getInstance()

    private val _listOfAddress = MutableLiveData<List<AddressUI>>()
    val listOfAddress: LiveData<List<AddressUI>> = _listOfAddress

    private val _popBackStack = MutableLiveData<Event<Boolean>>()
    val popBackStack: LiveData<Event<Boolean>> = _popBackStack

    init {
        getListOfAddresses()
    }


    fun getListOfAddresses() {
        viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e("http", throwable.toString())
            _screenState.postValue(ScreenState.ERROR)
        }) {
            var accessToken: String

            if (listOfAddress.value.isNullOrEmpty()) {
                _screenState.postValue(ScreenState.LOADING)
            }

            repository.getAccessToken().let {
                if (it.globalResponseStatus == GlobalResponseStatus.SUCCESS) {
                    accessToken = it.responseBody?.accessToken ?: ""
                } else {
                    _toastBundle.postValue(Event(ToastBundle(R.string.network_error)))
                    _screenState.postValue(ScreenState.ERROR)
                    return@launch
                }
            }

            addressApi.getAddressList(accessToken).let {
                if (it.globalResponseStatus == GlobalResponseStatus.SUCCESS) {
                    _listOfAddress.postValue(AddressesMapper.convertDtoToAddressUiModel(it.responseBody))
                    _screenState.postValue(ScreenState.DEFAULT)
                } else {
                    _screenState.postValue(ScreenState.ERROR)
                }
            }
        }
    }


    fun setCurrentAddressAsDefault(address: AddressUI) {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            _toastBundle.postValue(Event(ToastBundle(R.string.network_error)))
            Log.e("http", throwable.toString())
        }) {

            var accessToken: String

            repository.getAccessToken().let {
                if (it.globalResponseStatus == GlobalResponseStatus.SUCCESS) {
                    accessToken = it.responseBody?.accessToken ?: ""
                } else {
                    _toastBundle.postValue(Event(ToastBundle(R.string.network_error)))
                    return@launch
                }
            }

            // устанавливаем даты в адресе текущим днем, так как логика на сервере устанавливает адреса с
            // более новыми датами, как адресс по умолчанию
            address.apply {
                // Получаем дату текущего адреса по умолчанию
                val lastSavedDate =
                    listOfAddress.value?.first { it.isDefaultShippingAddress ?: false }
                        ?.dateAdded.parseToDate().orNow()

                // Увеличиваем самую новую дату, чтобы выбранный адрес точно был по умолчанию
                val updatedLastDate = Date().apply {
                    if (this <= lastSavedDate) time = lastSavedDate.time + 10000
                }

                defaultBilling = updatedLastDate.format()
                defaultShipping = updatedLastDate.format()
                dateAdded = updatedLastDate.format()
            }

            addressApi.updateClientAddress(
                accessToken,
                AddressesMapper.convertAddressUiModelToDto(address)
            ).let {
                if (it.globalResponseStatus == GlobalResponseStatus.SUCCESS) {
                    getListOfAddresses()
                    _toastBundle.postValue(Event(ToastBundle(R.string.set_default_address_success)))
                    _popBackStack.postValue(Event(true))
                } else {
                    _toastBundle.postValue(Event(ToastBundle(R.string.set_default_address_error)))
                }
            }
        }
    }
}