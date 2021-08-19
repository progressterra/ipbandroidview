package com.progressterra.ipbandroidview.ui.feeds

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.progressterra.ipbandroidview.ui.base.BaseViewModel
import com.progressterra.ipbandroidview.ui.feeds.models.FeedUi

class FeedsViewModel :
    BaseViewModel() {

    private val _feedsList = MutableLiveData<List<FeedUi>>()
    val feedsList: LiveData<List<FeedUi>> = _feedsList

    init {
        getFeeds()
    }

    fun getFeeds() {

    }


}