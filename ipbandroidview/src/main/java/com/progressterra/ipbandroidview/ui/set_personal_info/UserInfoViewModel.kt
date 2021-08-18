package com.progressterra.ipbandroidview.ui.set_personal_info

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidapi.remoteData.ipbAmbassador.models.ambassador_status.AmbassadorStatusResponse
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.data.IRepository
import com.progressterra.ipbandroidview.data.Repository
import com.progressterra.ipbandroidview.ui.set_personal_info.models.ClientInfo
import com.progressterra.ipbandroidview.ui.set_personal_info.models.ImageUpload
import com.progressterra.ipbandroidview.ui.set_personal_info.models.UserBankData
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ISResult
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.ScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.io.File
import java.io.InputStream


class UserInfoViewModel :
    ViewModel() {

    private val repository: IRepository = Repository()


    private val _ambassadorStateInfo = MutableLiveData<AmbassadorStatusResponse>()
    val ambassadorStateInfo: LiveData<AmbassadorStatusResponse> = _ambassadorStateInfo

    // поток байт для скачанного файла акта
    private val _inputStreamForDownloadedContractFile = MutableLiveData<InputStream>()
    val inputStreamForDownloadedContractFile: LiveData<InputStream> =
        _inputStreamForDownloadedContractFile

    // поток байт для скачанного файла акта для диалога шаринга
    private val _inputStreamForDownloadedContractFileForShare = MutableLiveData<InputStream>()
    val inputStreamForDownloadedContractFileForShare: LiveData<InputStream> =
        _inputStreamForDownloadedContractFileForShare

    // состояние загрузки для базовых данных профиля
    private val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState> = _screenState

    //b

    private val _becomeSelfEmployedBtnState = MutableLiveData<ScreenState>()
    val becomeSelfEmployedBtnState: LiveData<ScreenState> = _becomeSelfEmployedBtnState

    // состояние загрузки для изображения СНИЛСа
    private val _snilsImageUploadState = MutableLiveData<ISResult<ImageUpload>>()
    val snilsImageUploadState: LiveData<ISResult<ImageUpload>> =
        _snilsImageUploadState

    private val _contractImageUploadState = MutableLiveData<ISResult<ImageUpload>>()
    val contractImageUploadState: LiveData<ISResult<ImageUpload>> =
        _contractImageUploadState

    // состояние загрузки для изображения паспорта
    private val _passportImageUploadState = MutableLiveData<ISResult<ImageUpload>>()
    val passportImageUploadState: LiveData<ISResult<ImageUpload>> =
        _passportImageUploadState

    // модель данных экрана включающая в себя все поля а так же их валидность
    private val _allInfoUserModel = MutableLiveData(UserInfoModelUI())
    val allInfoUserModel: LiveData<UserInfoModelUI> = _allInfoUserModel

    // выводим снек на экран
    private val _showMessage = MutableLiveData<Event<Int>>()
    val showMessage: LiveData<Event<Int>> = _showMessage

    // обновляет статус кнопки "Обновить информацию" для базовых персональных данных (Имя/фамилия/отчество)
    private val _updateMainPersonalDataIsEnabled = MutableLiveData(false)
    val updateMainPersonalDataIsEnabled: LiveData<Boolean> =
        _updateMainPersonalDataIsEnabled

    // обновляет статус кнопки "Обновить информацию" для базовых персональных данных (Имя/фамилия/отчество)
    private val _updateBankPersonalDataIsEnabled = MutableLiveData(false)
    val updateBankPersonalDataIsEnabled: LiveData<Boolean> =
        _updateBankPersonalDataIsEnabled

    // содержит базовую информацию о пользователе, получаемую с сервера
    private val _onlyBaseUserInfo = MutableLiveData<ISResult<ClientInfo>>()
    val onlyBaseUserInfo: LiveData<ISResult<ClientInfo>> = _onlyBaseUserInfo

    // содержит банковскую информацию о пользователе, получаемую с сервера
    private val _onlyBankUserInfo = MutableLiveData<ISResult<UserBankData>>()
    val onlyBankUserInfo: LiveData<ISResult<UserBankData>> = _onlyBankUserInfo


    init {
        getMainUserInfo()
    }


    fun updateName(name: String) {
        _allInfoUserModel.value = allInfoUserModel.value?.copy(name = name)
        updateUploadMainDataButtonState()
    }

    fun updateLastName(lastName: String) {
        _allInfoUserModel.value = allInfoUserModel.value?.copy(lastName = lastName)
        updateUploadMainDataButtonState()
    }

    fun updateMiddleName(middleName: String) {
        _allInfoUserModel.value = allInfoUserModel.value?.copy(middleName = middleName)
        updateUploadMainDataButtonState()
    }

    fun updateBankName(bankName: String) {
        _allInfoUserModel.value = allInfoUserModel.value?.copy(bankName = bankName)
        updateUploadBankUserDataButtonState()
    }

    fun updateAccount(account: String) {
        _allInfoUserModel.value = allInfoUserModel.value?.copy(account = account)
        updateUploadBankUserDataButtonState()
    }

    fun updateBic(bic: String) {
        _allInfoUserModel.value = allInfoUserModel.value?.copy(bic = bic)
        updateUploadBankUserDataButtonState()
    }

    fun updateCorrespondentAccount(correspondentAccount: String) {
        _allInfoUserModel.value =
            allInfoUserModel.value?.copy(correspondentAccount = correspondentAccount)
        updateUploadBankUserDataButtonState()
    }

    fun updateInn(inn: String) {
        _allInfoUserModel.value = allInfoUserModel.value?.copy(inn = inn)
        updateUploadBankUserDataButtonState()
    }

    fun updateCpp(cpp: String) {
        _allInfoUserModel.value = allInfoUserModel.value?.copy(cpp = cpp)
        updateUploadBankUserDataButtonState()
    }

    // обновляет банковскую информацию о пользователе
    fun sendBaseUserInfo() {
        // проверяет, было ли успешно загружено фото. Маркером явлеяется наличие в префах ссылки на фото.
        // ее получаем в ответе на успешную загрузку его на сервер а затем сохраняем, чтобы подгрузить, когда пользователь
        // окажется на экране

        updateBankUserData()
    }

    // получает базовую информацию о пользователе
    fun getMainUserInfo() {
        viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d("test2", throwable.toString())
            _screenState.postValue(ScreenState.ERROR)
            _showMessage.postValue(Event(R.string.network_error))
        }) {
            _screenState.postValue(ScreenState.LOADING)
            var accessToken: String
            repository.getAccessToken().let {
                if (it.isSuccess()) {
                    accessToken = it.data!!
                } else {
                    _screenState.postValue(ScreenState.ERROR)
                    _showMessage.postValue(Event(R.string.network_error))
                    return@launch
                }
            }

            updateAmbassadorStatus(accessToken)

            // получаем базовую информацию о пользователе и обновляем глобальную модель данных для экрана
            repository.getClientInfo(accessToken).let {
                _onlyBaseUserInfo.postValue(it)
                if (it.isSuccess()) {
                    _allInfoUserModel.postValue(
                        allInfoUserModel.value?.copy(
                            name = it.data?.name ?: "",
                            lastName = it.data?.soname ?: "",
                            middleName = it.data?.patronymic ?: ""
                        )
                    )
                } else {
                    _screenState.postValue(ScreenState.ERROR)
                    _showMessage.postValue(Event(R.string.network_error))
                    return@launch
                }
            }

            // получаем банковскую информацию клиента
            repository.getBankClientInfo(accessToken).let {
                _onlyBankUserInfo.postValue(it)
                if (it.isSuccess()) {
                    _allInfoUserModel.postValue(
                        allInfoUserModel.value?.copy(
                            bankName = it.data?.bankName ?: "",
                            account = it.data?.numberAccount ?: "",
                            bic = it.data?.bik ?: "",
                            correspondentAccount = it.data?.correspondentAccount ?: "",
                            inn = it.data?.tinOfBank ?: "",
                            cpp = it.data?.kppBank ?: ""
                        )
                    )
                    _screenState.postValue(ScreenState.DEFAULT)
                } else {
                    _screenState.postValue(ScreenState.ERROR)
                    _showMessage.postValue(Event(R.string.network_error))
                    return@launch
                }
            }

        }
    }

    // загружаем фото паспорта
    fun uploadPassportImage(imageData: File) {
        viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
            _passportImageUploadState.postValue(SResult.Failed(""))
            _showMessage.postValue(Event(R.string.network_error))
        }) {
            _passportImageUploadState.postValue(SResult.Loading())
            var accessToken: String
            repository.getAccessToken().let {
                if (it.isSuccess()) {
                    accessToken = it.data!!
                } else {
                    _passportImageUploadState.postValue(SResult.Failed(""))
                    _showMessage.postValue(Event(R.string.network_error))
                    return@launch
                }
            }
            // загружаем фото на сервер и получаем URL по которому оно расположено.
            repository.uploadImage(accessToken, imageData = imageData).let { uploadImageResult ->
                if (uploadImageResult.isSuccess()) {

                    // ассоциируем возвращенный урл с текущим пользователем
                    repository.uploadPassportPhotoUrl(
                        uploadImageResult.data?.uploadedImageUrl!!,
                        accessToken
                    )
                        .let {
                            if (it.isSuccess()) {
                                //
                                updateAmbassadorStatus(accessToken)
                                _passportImageUploadState.postValue(uploadImageResult)
                                _showMessage.postValue(Event(R.string.passport_successully_updated))
                            } else {
                                _passportImageUploadState.postValue(SResult.Failed(""))
                                _showMessage.postValue(Event(R.string.network_error))
                                return@launch
                            }
                        }

                } else {
                    _passportImageUploadState.postValue(SResult.Failed(""))
                    _showMessage.postValue(Event(R.string.network_error))
                    return@launch
                }
            }
        }
    }

    // загрузка изображения СНИЛСа
    fun uploadSnilsImage(imageData: File) {
        viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
            _showMessage.postValue(Event(R.string.network_error))
            _snilsImageUploadState.postValue(SResult.Failed(""))
        }) {
            _snilsImageUploadState.postValue(SResult.Loading())

            var accessToken: String
            repository.getAccessToken().let {
                if (it.isSuccess()) {
                    accessToken = it.data!!
                } else {
                    _snilsImageUploadState.postValue(SResult.Failed(""))
                    _showMessage.postValue(Event(R.string.network_error))
                    return@launch
                }
            }

            repository.uploadImage(accessToken, imageData = imageData).let { uploadImageResult ->

                if (uploadImageResult.isSuccess()) {
                    repository.uploadSnilsPhotoUrl(
                        uploadImageResult.data?.uploadedImageUrl!!,
                        accessToken
                    ).let {
                        if (it.isSuccess()) {
                            _showMessage.postValue(Event(R.string.snils_successully_updated))
                            _snilsImageUploadState.postValue(uploadImageResult)
                            updateAmbassadorStatus(accessToken)
                        } else {
                            _snilsImageUploadState.postValue(SResult.Failed(""))
                            _showMessage.postValue(Event(R.string.network_error))
                            return@launch
                        }
                    }

                } else {
                    _snilsImageUploadState.postValue(SResult.Failed(""))
                    _showMessage.postValue(Event(R.string.network_error))
                    return@launch
                }
            }
        }
    }


    // загрузка изображения контракта с амбассадором
    fun uploadContractImage(imageData: File) {
        viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
            _showMessage.postValue(Event(R.string.network_error))
            _contractImageUploadState.postValue(SResult.Failed(""))
        }) {
            _contractImageUploadState.postValue(SResult.Loading())

            var accessToken: String
            repository.getAccessToken().let {
                if (it.isSuccess()) {
                    accessToken = it.data!!
                } else {
                    _contractImageUploadState.postValue(SResult.Failed(""))
                    _showMessage.postValue(Event(R.string.network_error))
                    return@launch
                }
            }

            repository.uploadImage(accessToken, imageData = imageData).let { uploadImageResult ->

                if (uploadImageResult.isSuccess()) {
                    repository.uploadAmbassadorContractPhotoUrl(
                        accessToken,
                        uploadImageResult.data?.uploadedImageUrl!!
                    ).let {
                        if (it.isSuccess()) {
                            _showMessage.postValue(Event(R.string.contract_successully_updated))
                            _contractImageUploadState.postValue(uploadImageResult)
                            updateAmbassadorStatus(accessToken)
                        } else {
                            _contractImageUploadState.postValue(SResult.Failed(""))
                            _showMessage.postValue(Event(R.string.network_error))
                            return@launch
                        }
                    }

                } else {
                    _contractImageUploadState.postValue(SResult.Failed(""))
                    _showMessage.postValue(Event(R.string.network_error))
                    return@launch
                }
            }
        }
    }


    // Реализация логики доступности кнопки обновления базовой информации о пользователе.
    // Кнопка доступна если каждое из трех полей прошло валидацию, а также пользователем были внесены изменения.
    // Поэтому сравниваем модель данных экрана и модель данных котороая пришла с сервера, а также убеждаемся что
    // данные в поле валидны
    private fun updateUploadMainDataButtonState() {
        when {
            onlyBaseUserInfo.value?.data?.name != allInfoUserModel.value?.name
                    && allInfoUserModel.value?.baseInfoIsValid == true ->
                _updateMainPersonalDataIsEnabled.value = true

            onlyBaseUserInfo.value?.data?.soname != allInfoUserModel.value?.lastName
                    && allInfoUserModel.value?.baseInfoIsValid == true ->
                _updateMainPersonalDataIsEnabled.value = true

            onlyBaseUserInfo.value?.data?.patronymic != allInfoUserModel.value?.middleName
                    && allInfoUserModel.value?.baseInfoIsValid == true
            ->
                _updateMainPersonalDataIsEnabled.value = true
            else -> _updateMainPersonalDataIsEnabled.value = false
        }
    }

    private fun updateUploadBankUserDataButtonState() {
        when {
            onlyBankUserInfo.value?.data?.bankName != allInfoUserModel.value?.bankName
                    && allInfoUserModel.value?.bankFieldsIsValid == true -> _updateBankPersonalDataIsEnabled.value =
                true
            onlyBankUserInfo.value?.data?.bik != allInfoUserModel.value?.bic
                    && allInfoUserModel.value?.bankFieldsIsValid == true -> _updateBankPersonalDataIsEnabled.value =
                true
            onlyBankUserInfo.value?.data?.numberAccount != allInfoUserModel.value?.account
                    && allInfoUserModel.value?.bankFieldsIsValid == true -> _updateBankPersonalDataIsEnabled.value =
                true
            onlyBankUserInfo.value?.data?.tinOfBank != allInfoUserModel.value?.inn
                    && allInfoUserModel.value?.bankFieldsIsValid == true -> _updateBankPersonalDataIsEnabled.value =
                true
            onlyBankUserInfo.value?.data?.correspondentAccount != allInfoUserModel.value?.correspondentAccount
                    && allInfoUserModel.value?.bankFieldsIsValid == true -> _updateBankPersonalDataIsEnabled.value =
                true
            onlyBankUserInfo.value?.data?.kppBank != allInfoUserModel.value?.cpp
                    && allInfoUserModel.value?.bankFieldsIsValid == true -> _updateBankPersonalDataIsEnabled.value =
                true
            else -> _updateBankPersonalDataIsEnabled.value = false
        }
    }

    // обновляем базовую информацию о пользователе
    fun updateMainUserData() {
        viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
            _showMessage.postValue(Event(R.string.personal_info_updating_error))
            _onlyBaseUserInfo.postValue(SResult.Failed(data = onlyBaseUserInfo.value?.data))
            _updateMainPersonalDataIsEnabled.postValue(true)
        }) {
            _onlyBaseUserInfo.postValue(SResult.Loading(data = onlyBaseUserInfo.value?.data))
            var accessToken: String
            _updateMainPersonalDataIsEnabled.postValue(false)

            repository.getAccessToken().let {
                if (it.isSuccess()) {
                    accessToken = it.data!!
                } else {
                    _showMessage.postValue(Event(R.string.network_error))
                    _updateMainPersonalDataIsEnabled.postValue(true)
                    _onlyBaseUserInfo.postValue(SResult.Failed(data = onlyBaseUserInfo.value?.data))
                    return@launch
                }
            }

            repository.updateClientInfo(
                accessToken,
                allInfoUserModel.value?.name ?: "",
                allInfoUserModel.value?.lastName ?: "",
                allInfoUserModel.value?.middleName ?: "",
            ).let {
                _onlyBaseUserInfo.postValue(it)
                if (it.isSuccess()) {
                    updateAmbassadorStatus(accessToken)
                    // в ответ приходит обновленная модель базовых данных, обновляем уже сохраненную
                    // чтобы могли корректно определить статус для кнопки обновления
                    _updateMainPersonalDataIsEnabled.postValue(false)
                    _showMessage.postValue(Event(R.string.personal_info_updating_success))
                } else {
                    _onlyBaseUserInfo.postValue(SResult.Failed(data = onlyBaseUserInfo.value?.data))
                    _updateMainPersonalDataIsEnabled.postValue(true)
                }
            }
        }
    }

    private fun updateBankUserData() {
        viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
            _showMessage.postValue(Event(R.string.personal_info_updating_error))
            _onlyBaseUserInfo.postValue(SResult.Failed(onlyBaseUserInfo.value?.data))
            _updateBankPersonalDataIsEnabled.postValue(true)
        }) {
            _onlyBankUserInfo.postValue(SResult.Loading(onlyBankUserInfo.value?.data))
            var accessToken: String
            _updateBankPersonalDataIsEnabled.postValue(false)

            repository.getAccessToken().let {
                if (it.isSuccess()) {
                    accessToken = it.data!!
                } else {
                    _showMessage.postValue(Event(R.string.personal_info_updating_error))
                    _updateBankPersonalDataIsEnabled.postValue(true)
                    _onlyBankUserInfo.postValue(SResult.Failed(onlyBankUserInfo.value?.data))
                    return@launch
                }
            }

            repository.updateBankClientInfo(
                accessToken,
                allInfoUserModel.value?.bankName ?: "",
                allInfoUserModel.value?.account ?: "",
                allInfoUserModel.value?.bic ?: "",
                allInfoUserModel.value?.correspondentAccount ?: "",
                allInfoUserModel.value?.inn ?: "",
                allInfoUserModel.value?.cpp ?: "",
            ).let {
                _onlyBankUserInfo.postValue(it)
                if (it.isSuccess()) {
                    updateAmbassadorStatus(accessToken)
                    // в ответ приходит обновленная модель банковских данных, обновляем уже сохраненную
                    // чтобы могли корректно определить статус для кнопки обновления
                    _updateBankPersonalDataIsEnabled.postValue(false)
                    _showMessage.postValue(Event(R.string.personal_info_updating_success))
                } else {
                    _onlyBankUserInfo.postValue(SResult.Failed(onlyBankUserInfo.value?.data))
                    _showMessage.postValue(Event(R.string.personal_info_updating_error))
                    _updateBankPersonalDataIsEnabled.postValue(true)
                }
            }
        }
    }

    private suspend fun updateAmbassadorStatus(accessToken: String) {
        repository.getAmbassadorStatus(accessToken).let {
            if (it.isSuccess()) {
                _ambassadorStateInfo.postValue(it.data!!)
            } else {
                _showMessage.postValue(Event(R.string.network_error))
            }
        }
    }

    fun becomeSelfEmployed() {
        viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
            _becomeSelfEmployedBtnState.postValue(ScreenState.ERROR)

        }) {
            _becomeSelfEmployedBtnState.postValue(ScreenState.LOADING)

            var accessToken: String
            repository.getAccessToken().let {
                if (it.isSuccess()) {
                    accessToken = it.data!!
                } else {
                    _becomeSelfEmployedBtnState.postValue(ScreenState.ERROR)
                    return@launch
                }
            }

            repository.becomeSelfEmployed(accessToken).let {
                if (it.isSuccess()) {
                    _becomeSelfEmployedBtnState.postValue(ScreenState.DEFAULT)
                    _ambassadorStateInfo.postValue(it.data!!)
                } else {
                    _becomeSelfEmployedBtnState.postValue(ScreenState.ERROR)

                    _showMessage.postValue(Event(R.string.network_error))
                }
            }
        }
    }


    fun downloadContractOfAmbassador(isForShare: Boolean) {
        viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->

        }) {
            var accessToken: String
            repository.getAccessToken().let {
                if (it.isSuccess()) {
                    accessToken = it.data!!
                } else {
                    return@launch
                }
            }

            repository.getContractOfAmbassador(accessToken).let {
                if (it.isSuccess()) {
                    if (isForShare) {
                        _inputStreamForDownloadedContractFileForShare.postValue(it.data?.byteStream())
                    } else {
                        _inputStreamForDownloadedContractFile.postValue(it.data?.byteStream())
                    }
                } else {

                }
            }
        }


    }

}