package com.progressterra.ipbandroidview.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.google.gson.Gson
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.BonusesApi
import com.progressterra.ipbandroidapi.localdata.shared_pref.UserData
import com.progressterra.ipbandroidapi.remoteData.iMessengerCore.IMessengerCore
import com.progressterra.ipbandroidapi.remoteData.iMessengerCore.models.DialogInfoRequest
import com.progressterra.ipbandroidapi.remoteData.iMessengerCore.models.MessageSendingRequest
import com.progressterra.ipbandroidapi.remoteData.iMessengerCore.models.additionalDataJSON
import com.progressterra.ipbandroidapi.utils.extentions.orIfNull
import com.progressterra.ipbandroidview.ui.base.BaseViewModel
import com.progressterra.ipbandroidview.ui.chat.utils.Message
import com.progressterra.ipbandroidview.ui.chat.utils.convertToMessagesList
import com.progressterra.ipbandroidview.utils.ScreenState
import kotlinx.coroutines.Job

class ChatViewModel(
    savedState: SavedStateHandle
) : BaseViewModel() {
    private val messengerApi = IMessengerCore()
    private val bonusesApi = BonusesApi.getInstance()

    private val idEnterprise: String = savedState.get<String>("idEnterprise")
        .orIfNull { throw NullPointerException("Did you forget to set idEnterprise?") }
    private val imageUrl: String = savedState.get<String>("imageUrl") ?: ""
    private val descriptionDialog: String = savedState.get<String>("descriptionDialog") ?: ""

    private var dialogId: String? = null

    private var messageUpdatingJob: Job? = null

    private var messageListPage = 0

    private val _messagesList = MutableLiveData<List<Message>>()
    internal val messageList: LiveData<List<Message>> = _messagesList

    private val _messageSandingStatus = MutableLiveData<ScreenState>()
    val messageSandingStatus: LiveData<ScreenState> = _messageSandingStatus


    init {

        getDialogInfo()
    }

    private fun getMessagesList(dialogId: String) {
        safeLaunchWithState {
            val response = messengerApi.getMessagesList(dialogId, messageListPage.toString())
            val messages = response.convertToMessagesList()
            _messagesList.postValue(messages)
        }
    }


    fun sendMessage(messageText: String) {
        if (messageText.isBlank()) return

        if (dialogId == null) {
            _screenState.value = ScreenState.ERROR
            return
        }

        safeLaunchWithState(state = _messageSandingStatus) {
            val token = bonusesApi.getAccessToken()
                .responseBody?.accessToken.orIfNull { throw NullPointerException("Token is null!") }

            val rawMessages = messengerApi
                .sendMessage(MessageSendingRequest(dialogId!!, token, messageText))
                .orIfNull { throw NullPointerException("Messages response is null") }
            val messages = rawMessages.convertToMessagesList()
            _messagesList.postValue(messages)
        }
    }


    // получаем информацию о диалоге между текущим пользователем и заданной организацией
    fun getDialogInfo() {
        safeLaunchWithState {
            val ids = listOf(UserData.clientInfo.idUnique, idEnterprise)
            val response = messengerApi.getDialogInfo(
                DialogInfoRequest(
                    listId = ids,
                    descriptionDialog = descriptionDialog,
                    additionalData = "",
                    additionalDataJSON = Gson().toJson(
                        additionalDataJSON(
                            idEnterprise,
                            imageUrl
                        )
                    )
                )
            )

            val dialogIdLocal: String = response.dialogInfo.idUnique
            dialogId = dialogIdLocal
            getMessagesList(dialogIdLocal)
        }
    }
}