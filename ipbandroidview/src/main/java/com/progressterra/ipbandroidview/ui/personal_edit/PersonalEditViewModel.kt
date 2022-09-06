package com.progressterra.ipbandroidview.ui.personal_edit

import androidx.lifecycle.*
import com.progressterra.ipbandroidapi.user.UserData
import com.progressterra.ipbandroidapi.utils.format
import com.progressterra.ipbandroidapi.utils.orIfNull
import com.progressterra.ipbandroidview.MainNavGraphDirections
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.data.prefs.UserDataLocal
import com.progressterra.ipbandroidview.ui.base.BaseBindingViewModel
import com.progressterra.ipbandroidview.ui.personal_edit.models.ClientInfoUI
import com.progressterra.ipbandroidview.ui.personal_edit.models.ClientUpdateInfo
import com.progressterra.ipbandroidview.usecases.scrm.GetClientCityUseCase
import com.progressterra.ipbandroidview.usecases.scrm.UpdateClientInfoUseCase
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.*

class PersonalEditViewModel(
    private val getClientCityUseCase: GetClientCityUseCase,
    private val updateClientInfoUseCase: UpdateClientInfoUseCase
) : BaseBindingViewModel() {

    val city: LiveData<SResult<String>> by lazyUnsafe {
        liveData {
            if (UserDataLocal.city.isEmpty()) {
                emit(loadingResult())
                getClientCityUseCase.getClientCity().onSuccess {
                    emit(it.toSuccessResult())
                }
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

            val result = updateClientInfoUseCase.updateInfo(
                ClientUpdateInfo(
                    name = name,
                    soname = soname,
                    email = email
                )
            )

            if (result.isSuccess) {
                _userData.postValue(result.getOrNull()!!.toSuccessResult())

                navLiveData.postValue(
                    MainNavGraphDirections.toInfoDialog(
                        "Отлично!",
                        "Изменения успешно сохранены",
                        "Ок",
                        R.drawable.ic_dialog_success
                    ).toNavResult()
                )
            } else {
                _userData.postValue(_userData.value?.data?.toSuccessResult())

                navLiveData.postValue(
                    MainNavGraphDirections.toInfoDialog(
                        "Упс, произошла ошибка",
                        "Попробуйте сохранить изменения ещё раз",
                        "Ок",
                        R.drawable.ic_dialog_failed
                    ).toNavResult()
                )
            }
        }
    }
}