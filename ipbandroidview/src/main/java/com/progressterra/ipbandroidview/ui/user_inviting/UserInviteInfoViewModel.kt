package com.progressterra.ipbandroidview.ui.user_inviting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidapi.api.ipbambassador.IPBAmbassador
import com.progressterra.ipbandroidview.data.CommonRepository
import com.progressterra.ipbandroidview.data.IRepository
import com.progressterra.ipbandroidview.ui.base.BaseBindingViewModel
import com.progressterra.ipbandroidview.ui.user_inviting.models.UserInviteDataUI
import com.progressterra.ipbandroidview.utils.SResult
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserInviteInfoViewModel(
    private val repository: IRepository.UserInviting
) :
    BaseBindingViewModel() {

    private val _infoForInvitingMembers = MutableLiveData<SResult<UserInviteDataUI>>()
    val infoForInvitingMembers: LiveData<SResult<UserInviteDataUI>> =
        _infoForInvitingMembers

    init {
        getInfo()
    }

    fun getInfo() {
        viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
            _infoForInvitingMembers.postValue(SResult.Failed())
        } + Dispatchers.IO) {
            _infoForInvitingMembers.postValue(SResult.Loading())

            repository.getInviteInfo().let {
                _infoForInvitingMembers.postValue(it)
            }

        }
    }
}