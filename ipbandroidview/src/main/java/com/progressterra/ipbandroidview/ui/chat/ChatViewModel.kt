package com.progressterra.ipbandroidview.ui.chat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidapi.interfaces.client.chat.ChatApi
import com.progressterra.ipbandroidapi.localdata.shared_pref.UserData
import com.progressterra.ipbandroidview.ui.base.BaseViewModel
import com.progressterra.ipbandroidview.ui.chat.utils.Message
import com.progressterra.ipbandroidview.ui.chat.utils.convertToMessagesList
import com.progressterra.ipbandroidview.utils.ScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ChatViewModel : BaseViewModel() {
    private val ambassadorApi = ChatApi()

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
        val handler = CoroutineExceptionHandler { context, exception ->
            Log.e("http", exception.toString())
        }
        viewModelScope.launch(handler) {

            tryWithState {
                val response = ambassadorApi.getMessagesList(
                    dialogId,
                    messageListPage.toString()
                ).responseBody
                val messages = response?.convertToMessagesList() ?: listOf()
                _messagesList.postValue(messages)

            }
            repository.getMessagesList(dialogId, messageListPage.toString()).let {
                if (it.resultIsSuccess()) {
                    _messagesList.postValue(it.data!!)
                    _screenState.postValue(ScreenState.DEFAULT)
                } else {
                    _screenState.postValue(ScreenState.ERROR)
                }
            }
        }
    }


    fun sendMessage(messageText: String) {
        if (messageText.isBlank()) return

        viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->

        }) {
            _messageSandingStatus.postValue(ScreenState.LOADING)

            if (dialogId == null) {
                _screenState.postValue(ScreenState.ERROR)
                return@launch
            }

            var accessToken: String
            repository.getAccessToken().let {
                if (it.status == Status.SUCCESS) {
                    accessToken = it.data!!
                } else {
                    _messageSandingStatus.postValue(ScreenState.ERROR)
                    return@launch
                }
            }

            repository.sendMessage(messageText, accessToken, dialogId!!).let {
                if (it.resultIsSuccess()) {
                    _messagesList.postValue(it.data!!)
                    _messageSandingStatus.postValue(ScreenState.DEFAULT)
                } else {
                    _messageSandingStatus.postValue(ScreenState.ERROR)
                }
            }
        }
    }


    // получаем информацию о диалоге между текущим пользователем и заданной организацией
    fun getDialogInfo() {

        viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
            _screenState.postValue(ScreenState.ERROR)
        }) {
            _screenState.postValue(ScreenState.LOADING)
            repository.getDialogInfo(
                UserData.clientInfo.idUnique, "50881abb-d4cd-4781-8259-b617f3cd23a2"
            ).let {
                if (it.resultIsSuccess()) {
                    dialogId = it.data
                    getMessagesList(it.data!!)
                } else {
                    _screenState.postValue(ScreenState.ERROR)
                }
            }


        }

    }
}