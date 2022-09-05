package com.progressterra.ipbandroidview.ui.bonuses_banner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.progressterra.ipbandroidview.data.IRepository
import com.progressterra.ipbandroidview.data.model.BonusesInfo
import com.progressterra.ipbandroidview.ui.base.BaseBindingViewModel
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.loadingResult

class BonusesBannerViewModel(
    private val repo: IRepository.Bonuses
) : BaseBindingViewModel() {

    private val _bonusesInfo = MutableLiveData<SResult<BonusesInfo>>(loadingResult())
    val bonusesInfo: LiveData<SResult<BonusesInfo>> = _bonusesInfo

    fun updateBonuses() {
        safeLaunch {
            _bonusesInfo.postValue(loadingResult())
            _bonusesInfo.postValue(repo.getBonusesInfo())
        }
    }
}