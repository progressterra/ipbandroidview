package com.progressterra.ipbandroidview.ui.chat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.progressterra.ipbandroidapi.localdata.shared_pref.UserData
import com.progressterra.ipbandroidapi.utils.extentions.orIfNull
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.data.ChatRepository
import com.progressterra.ipbandroidview.data.IRepozitory
import com.progressterra.ipbandroidview.ui.base.BaseViewModel
import com.progressterra.ipbandroidview.ui.chat.utils.Message
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.completedResult
import com.progressterra.ipbandroidview.utils.extensions.emptyFailed
import com.progressterra.ipbandroidview.utils.extensions.loadingResult
import com.progressterra.ipbandroidview.utils.extensions.toToastResult

class ChatViewModel(
    savedState: SavedStateHandle
) : BaseViewModel() {
    private val repo: IRepozitory.Chat = ChatRepository()

    private val idEnterprise: String = savedState.get<String>("idEnterprise")
        .orIfNull { throw NullPointerException("Did you forget to set idEnterprise?") }
    private val imageUrl: String = savedState.get<String>("imageUrl") ?: ""
    private val descriptionDialog: String = savedState.get<String>("descriptionDialog") ?: ""

    private var dialogId: String? = null

    private var messageListPage = 0

    val message = MutableLiveData<String>()

    private val _messagesList = MutableLiveData<SResult<List<Message>>>()
    val messagesList: LiveData<SResult<List<Message>>> = _messagesList

    private val _messageSendingStatus = MutableLiveData<SResult<*>>(completedResult())
    val messageSendingStatus: LiveData<SResult<*>> = _messageSendingStatus

    init {
        getDialogInfo()
    }

    private fun getMessagesList(dialogId: String) {
        safeLaunch {
            val messages = repo.getMessagesList(dialogId, messageListPage.toString())
            _messagesList.postValue(messages)
        }
    }

    fun sendMessage() {
        val messageText = message.value
        if (messageText.isNullOrEmpty())
            return

        Log.d("myTag", "$dialogId")
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
            message.postValue("")
            _messagesList.postValue(messages)
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