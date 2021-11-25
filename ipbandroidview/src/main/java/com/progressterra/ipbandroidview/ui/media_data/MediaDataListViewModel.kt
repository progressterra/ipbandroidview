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
import com.progressterra.ipbandroidview.utils.extensions.loadingResult
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream

class MediaDataListViewModel(
    savedState: SavedStateHandle
) : BaseBindingViewModel() {
    private val repo = MediaDataRepository()

    private val idEnterprise: String = savedState.get<String>("idEntity")
        .orIfNull { throw NullPointerException("Did you forget to set idEntity?") }

    private val _mediaDataList = MutableLiveData<SResult<List<MediaDataUi>>>()
    val mediaDataList: LiveData<SResult<List<MediaDataUi>>> = _mediaDataList

    private val _downloadedFileStream = MutableLiveData<InputStream>()
    val downloadedFileStream: LiveData<InputStream> =
        _downloadedFileStream

    init {
        fetchMediaDataList()
    }

    fun downloadFile(url: String?) {
        if (url == null) return
    }

    fun fetchMediaDataList() {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            Log.d("MediaDataListViewModel", throwable.toString())
        }) {

            _mediaDataList.postValue(loadingResult())
            _mediaDataList.postValue(repo.getMediaDataList(idEnterprise))
        }
    }


}