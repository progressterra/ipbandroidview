package com.progressterra.ipbandroidview.ui.addresses

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidapi.interfaces.client.addresses.AddressApi
import com.progressterra.ipbandroidapi.interfaces.client.addresses.AddressUI
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.BonusesApi
import com.progressterra.ipbandroidapi.remoteData.models.base.GlobalResponseStatus
import com.progressterra.ipbandroidapi.remoteData.scrm.models.address.dadata.Suggestion
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.ui.base.BaseViewModel
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ScreenState
import com.progressterra.ipbandroidview.utils.ToastBundle
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch


class AddNewAddressViewModel :
    BaseViewModel() {

    private val addressApi = AddressApi()
    private val repository = BonusesApi.getInstance()

    private val _suggestions = MutableLiveData<List<Suggestion>>()
    val suggestions: LiveData<List<Suggestion>> = _suggestions

    private val _address = MutableLiveData<AddressUI>()
    val address: LiveData<AddressUI> = _address

    private val _popBackStack = MutableLiveData<Event<Boolean>>()
    val popBackStack: LiveData<Event<Boolean>> = _popBackStack

    private val _addAddressStatus = MutableLiveData<ScreenState>()
    val addAddressStatus: LiveData<ScreenState> = _addAddressStatus

    private val _addAddressButtonEnabled = MutableLiveData<Boolean>(false)
    val addAddressButtonEnabled: LiveData<Boolean> = _addAddressButtonEnabled

    // получение списка подсказок
    fun getSuggestions(query: String) {
        viewModelScope.launch {
            addressApi.getSuggestionsAddressFromDadata(query).let {
                if (it.globalResponseStatus == GlobalResponseStatus.SUCCESS) {
                    _suggestions.postValue(it.responseBody?.suggestions ?: emptyList())
                } else {
                    _toastBundle.postValue(Event(ToastBundle(R.string.suggestions_error)))
                }
            }
        }
    }

    // добавление нового адреса
    fun addNewAddress() {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            Log.d("http", throwable.toString())
            _addAddressStatus.postValue(ScreenState.ERROR)
            _toastBundle.postValue(Event(ToastBundle(R.string.add_address_error)))
        }) {
            if (address.value == null) return@launch

            if (addAddressStatus.value == ScreenState.LOADING) {
                return@launch
            }

            _addAddressStatus.postValue(ScreenState.LOADING)

            var accessToken: String

            repository.getAccessToken().let {
                if (it.globalResponseStatus == GlobalResponseStatus.SUCCESS) {
                    accessToken = it.responseBody?.accessToken ?: ""
                } else {
                    _toastBundle.postValue(Event(ToastBundle(R.string.add_address_error)))
                    _addAddressStatus.postValue(ScreenState.ERROR)
                    return@launch
                }
            }

            addressApi.addClientAddress(accessToken, address.value!!).let {
                if (it.globalResponseStatus == GlobalResponseStatus.SUCCESS) {
                    _toastBundle.postValue(Event(ToastBundle(R.string.add_address_success)))
                    _popBackStack.postValue(Event(true))
                    _addAddressStatus.postValue(ScreenState.DEFAULT)
                } else {
                    _toastBundle.postValue(Event(ToastBundle(R.string.add_address_error)))
                    _addAddressStatus.postValue(ScreenState.ERROR)
                }
            }
        }
    }

    fun setAddressFromSuggestion(suggestion: Suggestion) {
        _address.value = suggestion.data?.convertToUIModel()
        addressValidation()
    }

    // установка эатажа
    fun setFloor(floor: String) {
        _address.postValue(address.value?.copy(floor = floor))
    }

    // установка номера квартиры
    fun setFlatNumber(number: String) {
        _address.postValue(address.value?.copy(apartment = number))
    }

    // установка номера подъезда
    fun setEntranceNumber(number: String) {
        _address.postValue(address.value?.copy(entrance = number))
    }

    private fun addressValidation() {
        address.value?.let {
            val addressIsValid =
                !it.nameCity.isNullOrBlank() && !it.nameStreet.isNullOrBlank() && !it.houseNUmber.isNullOrBlank()
            _addAddressButtonEnabled.postValue(addressIsValid)
        }


    }

}