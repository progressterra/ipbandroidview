package com.progressterra.ipbandroidview.ui.user_inviting

import androidx.lifecycle.*
import com.progressterra.ipbandroidapi.api.ipbambassador.IPBAmbassador
import com.progressterra.ipbandroidapi.api.ipbambassador.models.invite_members.InvitingMembersRequest
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.data.IRepository
import com.progressterra.ipbandroidview.ui.base.BaseBindingViewModel
import com.progressterra.ipbandroidview.ui.user_inviting.models.ContactUi
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.*
import kotlinx.coroutines.launch

class InviteUserViewModel : BaseBindingViewModel() {
    val sendingInviteState: MutableLiveData<SResult<String>> = MutableLiveData()

    private var repository =
        IRepository.UserInviting(IPBAmbassador.AmbassadorInvite())

    val resultText: LiveData<String> = sendingInviteState.switchMap {
        liveData {
            when (it) {
                is SResult.Failed -> emit("Что-то пошло не так:\n${it.message}\nПопробуйте отправить приглашения еще раз")
                is SResult.Completed -> emit("Отлично!\nПриглашения успешно отправлены!")
                is SResult.Success -> {
                    emit(it.data)
                }
                else -> {
                    emit("")
                }
            }
        }
    }

    private var mContacts = mutableListOf<ContactUi>()

    val search = MutableLiveData("")

    private val _contacts = MutableLiveData<List<ContactUi>>(emptyList())

    val contacts: MediatorLiveData<List<ContactUi>> by lazy {
        val ld = MediatorLiveData<List<ContactUi>>()
        ld.addSource(_contacts) {
            ld.postValue(filterContacts(it, search.value))
        }
        ld.addSource(search) {
            ld.postValue(filterContacts(_contacts.value, it))
        }

        ld
    }

    val selectedContacts: LiveData<List<ContactUi>> = contacts.switchMap {
        liveData {
            emit(mContacts.filter { it.isSelected })
        }
    }

    fun clearSearchQuery() {
        search.value = ""
    }

    fun selectContact(contactUi: ContactUi) {
        mContacts[mContacts.indexOf(contactUi)] = contactUi

        _contacts.value = mContacts
    }

    fun sendInvites(isRetry: Boolean = false) {
        viewModelScope.launch {

            var accessToken: String
            sendingInviteState.postValue(SResult.Loading(""))
            repository

            repository.getAccessToken().let {
                if (it is SResult.Success) {
                    accessToken = it.data
                } else {
                    toastLiveData.postValue(SResult.Toast(R.string.network_error))
                    return@launch
                }
            }

            repository.sendInvites(
                InvitingMembersRequest(
                    accessToken,
                    selectedContacts.value?.map {
                        if (it.number?.startsWith("+") == true) {
                            it.number?.drop(2) ?: ""
                        } else {
                            it.number?.drop(1) ?: ""
                        }

                    } ?: emptyList()
                )
            ).let { it ->
                if (it.isSuccess()) {

                    val successfulInvitedContacts = mutableListOf<ContactUi>()
                    val unsuccessfulInvitedContacts = mutableListOf<ContactUi>()

                    // ищем совпадение по номеру успешно приглашенных контактов со всеми, чтобы получить имя для отображения далее
                    it.data?.listSuccessfulInvite?.map { successfulInvitedContactNumber ->
                        successfulInvitedContacts.addAll(mContacts.filter {
                            it.number?.contains(
                                successfulInvitedContactNumber
                            ) ?: false
                        })
                    }

                    // ищем совпадение по номеру НЕуспешно приглашенных контактов со всеми, чтобы получить имя для отображения далее
                    it.data?.listRejectedInvite?.map { unsuccessfulInvitedContactNumber ->
                        unsuccessfulInvitedContacts.addAll(mContacts.filter {
                            it.number?.contains(
                                unsuccessfulInvitedContactNumber
                            ) ?: false
                        })
                    }

                    // формируем текст для успешно приглашенных пользователей
                    val userInvitingResultTextSb: StringBuilder = StringBuilder()

                    if (successfulInvitedContacts.isNotEmpty()) {

                        if (successfulInvitedContacts.size == 1) {
                            userInvitingResultTextSb.append("Пользователю ")
                        } else {
                            userInvitingResultTextSb.append("Пользователям ")
                        }

                        for (contact in successfulInvitedContacts.withIndex()) {
                            if (contact.index == successfulInvitedContacts.lastIndex)
                                userInvitingResultTextSb.append("${contact.value.name} ") else {
                                userInvitingResultTextSb.append("${contact.value.name}, ")
                            }
                        }
                        userInvitingResultTextSb.append("приглашение успешно отправлено")
                    }


                    // формируем текст для неуспешно приглашенных пользователей
                    if (unsuccessfulInvitedContacts.isNotEmpty()) {
                        userInvitingResultTextSb.append("\n")
                        if (unsuccessfulInvitedContacts.size == 1) {
                            userInvitingResultTextSb.append("Пользователь ")
                        } else {
                            userInvitingResultTextSb.append("Пользователи ")
                        }

                        for (contact in unsuccessfulInvitedContacts.withIndex()) {
                            if (contact.index == unsuccessfulInvitedContacts.lastIndex)
                                userInvitingResultTextSb.append("${contact.value.name} ") else {
                                userInvitingResultTextSb.append("${contact.value.name}, ")
                            }
                        }
                        if (unsuccessfulInvitedContacts.size == 1) {
                            userInvitingResultTextSb.append("уже является участником программы лояльности")
                        } else {
                            userInvitingResultTextSb.append("уже являются участником программы лояльности")
                        }

                    }

                    sendingInviteState.postValue(SResult.Success(userInvitingResultTextSb.toString()))
                    if (!isRetry) {
                        navLiveData.value = ContactListFragmentDirections.toResult().toNavResult()
                    }

                } else if (it is SResult.Failed) {
                    sendingInviteState.postValue(
                        SResult.Failed(
                            it.message
                        )
                    )
                    if (!isRetry) {
                        navLiveData.value = ContactListFragmentDirections.toResult().toNavResult()
                    }

                }
            }
        }

    }

    fun setContacts(contacts: MutableList<ContactUi>) {
        mContacts = contacts
        _contacts.value = mContacts
    }

    private fun filterContacts(list: List<ContactUi>?, srch: String?): List<ContactUi>? {
        list ?: return list

        return if (srch.isNullOrBlank()) {
            list
        } else {
            list.filter { it.name?.contains(srch, ignoreCase = true) ?: false }
        }
    }

    fun onActionButton() {
        if (sendingInviteState.value?.isError() == false) {
            navLiveData.value = SendingInviteFinishDialogDirections.toInvite().toNavResult()
        } else {
            sendInvites(isRetry = true)
        }
    }

    fun onDestroyView() {
        mContacts.forEach { it.isSelected = false }
        _contacts.value = mContacts
    }
}