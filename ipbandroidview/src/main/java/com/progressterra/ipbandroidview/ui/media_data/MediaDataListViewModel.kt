package com.progressterra.ipbandroidview.ui.media_data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidapi.utils.extentions.orIfNull
import com.progressterra.ipbandroidview.data.MediaDataRepository
import com.progressterra.ipbandroidview.ui.base.BaseBindingViewModel
import com.progressterra.ipbandroidview.ui.media_data.models.MediaDataUi
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.emptyFailed
import com.progressterra.ipbandroidview.utils.extensions.loadingResult
import com.progressterra.ipbandroidview.utils.extensions.toSuccessResult
import com.progressterra.ipbandroidview.utils.extensions.toToastResult
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream

class MediaDataListViewModel(
    savedState: SavedStateHandle
) : BaseBindingViewModel() {
    private val repo = MediaDataRepository()

    private val mediaDataSettings: String =
        savedState.get<String>("entityId")
            .orIfNull { throw NullPointerException("Did you forget to set idEntity?") }

    private val _mediaDataList = MutableLiveData<SResult<List<MediaDataUi>>>()
    val mediaDataList: LiveData<SResult<List<MediaDataUi>>> = _mediaDataList

    private val _downloadedFileStream = MutableLiveData<SResult<InputStream>>()
    val downloadedFileStream: LiveData<SResult<InputStream>> =
        _downloadedFileStream

    init {
        fetchMediaDataList()
    }

    fun downloadFile(url: String?) {
        if (url == null) return
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, _ ->
            toastLiveData.postValue("Ошибка при скачивании файла".toToastResult())
        }) {
            repo.downloadFile(url).let {
                if (it is SResult.Success) {
                    _downloadedFileStream.postValue((it.data.byteStream()).toSuccessResult().apply {
                        isNeedHandle = true
                    })
                } else {
                    toastLiveData.postValue("Ошибка при скачивании файла".toToastResult())
                }
            }
        }
    }

    fun fetchMediaDataList() {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            _mediaDataList.postValue(emptyFailed())
            Log.e("MediaDataListViewModel", throwable.toString())
        }) {
            _mediaDataList.postValue(loadingResult())
            _mediaDataList.postValue(repo.getMediaDataList(mediaDataSettings))
        }
    }


}