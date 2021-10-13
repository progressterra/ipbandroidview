package com.progressterra.ipbandroidview.ui.personal_edit

import android.util.Log
import androidx.lifecycle.*
import com.progressterra.ipbandroidapi.localdata.shared_pref.UserData
import com.progressterra.ipbandroidapi.utils.extentions.orIfNull
import com.progressterra.ipbandroidview.data.prefs.UserDataLocal
import com.progressterra.ipbandroidview.ui.base.BaseBindingViewModel
import com.progressterra.ipbandroidview.ui.chat.utils.format
import com.progressterra.ipbandroidview.ui.personal_edit.models.ClientInfoUI
import com.progressterra.ipbandroidview.ui.personal_edit.models.ClientUpdateInfo
import com.progressterra.ipbandroidview.usecases.client.IGetClientCityUseCase
import com.progressterra.ipbandroidview.usecases.client.IUpdateClientPersonalDataUseCase
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.*

class PersonalEditViewModel : BaseBindingViewModel() {

    private val getCityUseCase: IGetClientCityUseCase =
        IGetClientCityUseCase.IGetClientCityUseCase()

    private val updateDataUseCase: IUpdateClientPersonalDataUseCase =
        IUpdateClientPersonalDataUseCase.IUpdateClientPersonalDataUseCase()

    val city: LiveData<SResult<String>> by lazyUnsafe {
        liveData {
            if (UserDataLocal.city.isEmpty()) {
                emit(loadingResult())
                emit(getCityUseCase.invoke())
            } else {
                emit(UserDataLocal.city.toSuccessResult())
            }
        }
    }

    private val _userData: MutableLiveData<SResult<ClientInfoUI>> = MutableLiveData(
        ClientInfoUI(
            UserData.fullName,
            UserData.email,
            UserData.clientInfo.name,
            UserData.clientInfo.soname
        ).toSuccessResult()
    )
    val userData: LiveData<SResult<ClientInfoUI>> by lazy {
        Log.d("myTag", "ud = ${_userData.value?.data}")
        _userData
    }

    val name: MutableLiveData<String> by lazyUnsafe {
        MutableLiveData(_userData.value?.data?.name)
    }
    val nameIsValid = name.map { it.isNotBlank() }

    val lastName: MutableLiveData<String> by lazyUnsafe {
        MutableLiveData(_userData.value?.data?.lastName)
    }
    val lastNameIsValid: LiveData<Boolean> by lazyUnsafe {
        lastName.map { it.isNotBlank() }
    }

    val phone: LiveData<String> by lazyUnsafe {
        liveData {
            emit("+${UserData.clientAdditionalInfo.phoneGeneral}")
        }
    }

    val birthDay: LiveData<String> by lazyUnsafe {
        liveData {
            emit(UserData.clientInfo.dateOfBirth?.format("dd.MM.yyyy") ?: "")
        }
    }

    val email: MutableLiveData<String> by lazyUnsafe {
        MutableLiveData(_userData.value?.data?.email)
    }
    val emailIsValid = email.map { (Regex(".+@.+\\..+")).matches(it) }

    val dataIsValid: LiveData<Boolean> by lazyUnsafe {
        nameIsValid.switchMap { name ->
            lastNameIsValid.switchMap { lastName ->
                emailIsValid.switchMap { email ->
                    liveData {
                        emit(name && lastName && email)
                    }
                }
            }
        }
    }

    fun onUpdateData() {
        safeLaunch {
            val name = name.value.orIfNull { return@safeLaunch }
            val soname = lastName.value.orIfNull { return@safeLaunch }
            val email = email.value.orIfNull { return@safeLaunch }

            _userData.postValue(_userData.value?.data.toLoadingResult())

            val result = updateDataUseCase.invoke(
                ClientUpdateInfo(
                    name = name,
                    soname = soname,
                    email = email
                )
            )

            if (result is SResult.Success) {
                _userData.postValue(result.data.toSuccessResult())
            } else {
                _userData.postValue(_userData.value?.data?.toSuccessResult())
                toastLiveData.postValue((result as? SResult.Failed)?.message?.toToastResult())
            }
        }
    }
}