package com.progressterra.ipbandroidview.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.progressterra.ipbandroidapi.localdata.shared_pref.UserData
import com.progressterra.ipbandroidapi.utils.extentions.orIfNull
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.data.ChatRepository
import com.progressterra.ipbandroidview.data.IRepository
import com.progressterra.ipbandroidview.ui.base.BaseViewModel
import com.progressterra.ipbandroidview.ui.chat.utils.MessageWithDateUI
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.*

class ChatDrawableViewModel(
    savedState: SavedStateHandle
) : BaseViewModel() {
    private val repo: IRepository.Chat = ChatRepository()

    private val idEnterprise: String = savedState.get<String>("idEnterprise")
        .orIfNull { throw NullPointerException("Did you forget to set idEnterprise?") }
    private val imageUrl: String = savedState.get<String>("imageUrl") ?: ""
    private val descriptionDialog: String = savedState.get<String>("descriptionDialog") ?: ""

    private var dialogId: String? = null

    private var messageListPage = 0

    val message = MutableLiveData<String>()

    private val _messagesList = MutableLiveData<SResult<List<MessageWithDateUI>>>()
    val messagesList: LiveData<SResult<List<MessageWithDateUI>>> = _messagesList

    private val _messageSendingStatus = MutableLiveData<SResult<*>>(completedResult())
    val messageSendingStatus: LiveData<SResult<*>> = _messageSendingStatus

    init {
        getDialogInfo()
    }

    private fun getMessagesList(dialogId: String) {
        safeLaunch {
            val messages = repo.getMessagesList(dialogId, messageListPage.toString())
            val converted = MessageWithDateUI.convertToTransactionsWithDate(messages.data)
            _messagesList.postValue(converted.toSuccessResult())
        }
    }

    fun sendMessage() {
        val messageText = message.value
        if (messageText.isNullOrEmpty())
            return

        if (dialogId == null) {
            _messagesList.postValue(emptyFailed())
            return
        }

        safeLaunch(onCatch = {
            _messageSendingStatus.postValue(R.string.message_send_error.toToastResult())
        }) {
            _messageSendingStatus.postValue(loadingResult())
            val token = repo.getAccessToken().data.orEmpty()
            val messages = repo.sendMessage(messageText, token, dialogId!!)
            val converted = MessageWithDateUI.convertToTransactionsWithDate(messages.data)
            message.postValue("")
            _messagesList.postValue(converted.toSuccessResult())
            _messageSendingStatus.postValue(completedResult())
        }
    }

    // получаем информацию о диалоге между текущим пользователем и заданной организацией
    fun getDialogInfo() {
        safeLaunch(onCatch = {
            _messagesList.postValue(emptyFailed())
        }) {
            _messagesList.postValue(loadingResult())
            val result = repo.getDialogInfo(
                UserData.clientInfo.idUnique,
                idEnterprise,
                descriptionDialog,
                imageUrl
            )

            when (result) {
                is SResult.Success -> {
                    dialogId = result.data
                    getMessagesList(result.data)
                }
                else -> _messagesList.postValue(emptyFailed())
            }
        }
    }
}