package com.progressterra.ipbandroidview.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidapi.api.ipbAmbassador.IPBAmbassador
import com.progressterra.ipbandroidview.data.IRepository
import com.progressterra.ipbandroidview.ui.base.BaseBindingViewModel
import com.progressterra.ipbandroidview.ui.contacts.models.UserInviteDataUI
import com.progressterra.ipbandroidview.utils.SResult
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserInviteInfoViewModel :
    BaseBindingViewModel() {

    private val _infoForInvitingMembers = MutableLiveData<SResult<UserInviteDataUI>>()
    val infoForInvitingMembers: LiveData<SResult<UserInviteDataUI>> =
        _infoForInvitingMembers

    private var repository = IRepository.UserInviting(IPBAmbassador.AmbassadorInvite())

    init {
        getInfo()
    }

    fun getInfo() {
        viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable -> } + Dispatchers.IO) {
            _infoForInvitingMembers.postValue(SResult.Loading())

            var accessToken: String

            repository.getAccessToken().let {
                if (it.isSuccess()) {
                    accessToken = it.data ?: ""
                } else {
                    _infoForInvitingMembers.postValue(SResult.Failed())
                    return@launch
                }
            }

            repository.getInviteInfo(accessToken).let {
                _infoForInvitingMembers.postValue(it)
            }

        }
    }
}