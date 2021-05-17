package com.progressterra.android.ipbandroidview.bonuses_banner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidapi.interactors.BonusesBannerInteractor
import com.progressterra.ipbandroidapi.repository.models.GeneralInfoResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BonusesBannerViewModel : ViewModel() {

    val bonusesInfo = MutableLiveData<GeneralInfoResponse>()
    val bonusesCountIsZero = MutableLiveData(false)

    init {
        updateBonusesInfo()
    }

    fun updateBonusesInfo() {
        CoroutineScope(Job()).launch {
            val bonusesInfoResponse = BonusesBannerInteractor().getBonusesInfo().body()
            bonusesInfoResponse?.let {
                if (it.data.currentQuantity == 0.0) {
                    bonusesCountIsZero.postValue(false)
                } else {
                    bonusesCountIsZero.postValue(true)
                }
            }
        }
    }

}