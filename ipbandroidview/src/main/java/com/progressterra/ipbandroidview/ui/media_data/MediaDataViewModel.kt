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
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream

class MediaDataViewModel(
    savedState: SavedStateHandle
) : BaseBindingViewModel() {
    private val repo = MediaDataRepository()

    private val mediaDataSettings: String =
        savedState.get<String>("mediaDataId")
            .orIfNull { throw NullPointerException("Did you forget to set mediaDataId?") }

    private val _mediaData = MutableLiveData<SResult<MediaDataUi>>()
    val mediaData: LiveData<SResult<MediaDataUi>> = _mediaData

    private val _downloadedFileStream = MutableLiveData<InputStream>()
    val downloadedFileStream: LiveData<InputStream> =
        _downloadedFileStream

    init {
        fetchMediaData()
    }

    fun downloadFile(url: String?) {
        if (url == null) return
    }

    fun fetchMediaData() {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            _mediaData.postValue(emptyFailed())
            Log.e("MediaDataViewModel", throwable.toString())
        }) {

            _mediaData.postValue(loadingResult())
            _mediaData.postValue(repo.getMediaData(mediaDataSettings))
        }
    }

}