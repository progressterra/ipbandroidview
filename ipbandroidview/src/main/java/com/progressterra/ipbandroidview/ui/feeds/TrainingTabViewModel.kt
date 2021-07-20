package com.progressterra.ipbandroidview.ui.feeds

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidview.utils.ScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class TrainingTabViewModel() :
    ViewModel() {

    val feedsList = MutableLiveData<List<FeedUiModel>>()
    val screenState = MutableLiveData<ScreenState>()

    init {
        getProductInfo()
    }

    fun getProductInfo(IDRGGoodsInventory: String = "1d249e26-0a26-4ab5-b695-4e9294f99818") {

        viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
            screenState.postValue(ScreenState.ERROR)
        }) {
            screenState.postValue(ScreenState.LOADING)

        }
    }


}