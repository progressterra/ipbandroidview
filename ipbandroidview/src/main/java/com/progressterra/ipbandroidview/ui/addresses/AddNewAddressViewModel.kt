package com.progressterra.ipbandroidview.ui.addresses

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidapi.api.address.AddressRepository
import com.progressterra.ipbandroidapi.api.ibonus.IBonusRepository
import com.progressterra.ipbandroidview.ui.addresses.models.AddressUI
import com.progressterra.ipbandroidview.ui.addresses.models.AddressesMapper
import com.progressterra.ipbandroidview.ui.addresses.models.SuggestionUI
import com.progressterra.ipbandroidview.ui.base.BaseViewModel
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ScreenState

class AddNewAddressViewModel(
    private val addressRepository: AddressRepository,
    private val iBonusRepository: IBonusRepository
) : BaseViewModel() {

    private val _suggestions = MutableLiveData<List<SuggestionUI>>()
    val suggestions: LiveData<List<SuggestionUI>> = _suggestions

    private val _address = MutableLiveData(AddressUI())
    val address: LiveData<AddressUI> = _address

    private val _popBackStack = MutableLiveData<Event<Boolean>>()
    val popBackStack: LiveData<Event<Boolean>> = _popBackStack

    private val _addAddressStatus = MutableLiveData<ScreenState>()
    val addAddressStatus: LiveData<ScreenState> = _addAddressStatus

    private val _addAddressButtonEnabled = MutableLiveData<Boolean>(false)
    val addAddressButtonEnabled: LiveData<Boolean> = _addAddressButtonEnabled

    // TODO получение списка подсказок
    fun getSuggestions(query: String) {
//        viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
//            Log.e("http", throwable.toString())
//        }) {
//            address.getSuggestionsAddressFromDadata(query).let {
//                if (it.globalResponseStatus == GlobalResponseStatus.SUCCESS) {
//                    _suggestions.postValue(AddressesMapper.convertSuggestionsDtoToUIModels(it.responseBody?.suggestions))
//                } else {
//                    _toastBundle.postValue(Event(ToastBundle(R.string.suggestions_error)))
//                }
//            }
//        }
    }

    // добавление нового адреса
    fun addNewAddress() {
//        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
//            Log.e("http", throwable.toString())
//            _addAddressStatus.postValue(ScreenState.ERROR)
//            _toastBundle.postValue(Event(ToastBundle(R.string.add_address_error)))
//        }) {
//            val mAddress = address.value ?: return@launch
//            // устанавливаем даты в адресе текущим днем, так как логика на сервере устанавливает адреса с
//            // более новыми датами, как адресс по умолчанию
//
//            mAddress.apply {
//                val currentDate = Date()
//                defaultBilling = currentDate.format("yyyy-MM-dd'T'HH:mm:ss")
//                defaultShipping = currentDate.format("yyyy-MM-dd'T'HH:mm:ss")
//                dateAdded = currentDate.format("yyyy-MM-dd'T'HH:mm:ss")
//            }
//
//            if (addAddressStatus.value == ScreenState.LOADING) {
//                return@launch
//            }
//
//            _addAddressStatus.postValue(ScreenState.LOADING)
//
//            var accessToken: String
//
//            repository.getAccessToken().let {
//                if (it.globalResponseStatus == GlobalResponseStatus.SUCCESS) {
//                    accessToken = it.responseBody?.accessToken ?: ""
//                } else {
//                    _toastBundle.postValue(Event(ToastBundle(R.string.add_address_error)))
//                    _addAddressStatus.postValue(ScreenState.ERROR)
//                    return@launch
//                }
//            }
//
//
//
//            addressApi.addClientAddress(
//                accessToken,
//                AddressesMapper.convertAddressUiModelToDto(mAddress)
//            ).let {
//                if (it.globalResponseStatus == GlobalResponseStatus.SUCCESS) {
//                    _toastBundle.postValue(Event(ToastBundle(R.string.add_address_success)))
//                    _popBackStack.postValue(Event(true))
//                    _addAddressStatus.postValue(ScreenState.DEFAULT)
//                } else {
//                    _toastBundle.postValue(Event(ToastBundle(R.string.add_address_error)))
//                    _addAddressStatus.postValue(ScreenState.ERROR)
//                }
//            }
//        }
    }

    fun setAddressFromSuggestion(suggestion: SuggestionUI) {
//        _address.value =
//            suggestion.suggestionExtendedInfo?.let {
//                AddressesMapper.convertSuggestionToAddressUIModel(
//                    it
//                )
//            }
//        addressValidation()
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