package com.progressterra.ipbandroidview.ui.media_data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidapi.utils.orIfNull
import com.progressterra.ipbandroidview.data.IRepository
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

class MediaDataViewModel(
    private val mediaDataId: String,
    private val repo: IRepository.MediaData
) : BaseBindingViewModel() {

    private val _mediaData = MutableLiveData<SResult<MediaDataUi>>()
    val mediaData: LiveData<SResult<MediaDataUi>> = _mediaData

    private val _downloadedFileStream = MutableLiveData<SResult<InputStream>>()
    val downloadedFileStream: LiveData<SResult<InputStream>> =
        _downloadedFileStream

    init {
        fetchMediaData()
    }

    fun downloadFile(url: String?) {
        if (url == null) return
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { coroutineContext, throwable ->
            toastLiveData.postValue("Ошибка при скачивании файла".toToastResult())
        }) {

            repo.downloadFile(url).let {
                if (it is SResult.Success) {
                    _downloadedFileStream.postValue((it.data.byteStream().toSuccessResult()).apply {
                        isNeedHandle = true
                    })
                } else {
                    toastLiveData.postValue("Ошибка при скачивании файла".toToastResult())
                }
            }
        }
    }

    fun fetchMediaData() {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            _mediaData.postValue(emptyFailed())
            Log.e("MediaDataViewModel", throwable.toString())
        }) {

            _mediaData.postValue(loadingResult())
            _mediaData.postValue(repo.getMediaData(mediaDataId))
        }
    }

}