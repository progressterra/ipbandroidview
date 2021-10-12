package com.progressterra.ipbandroidview.ui.bonuses_banner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.models.BonusesInfo
import com.progressterra.ipbandroidview.data.CommonRepository
import com.progressterra.ipbandroidview.data.IRepository
import com.progressterra.ipbandroidview.ui.base.BaseBindingViewModel
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.loadingResult

class BonusesBannerViewModel : BaseBindingViewModel() {
    private val repo: IRepository.Bonuses = CommonRepository()

    private val _bonusesInfo = MutableLiveData<SResult<BonusesInfo>>(loadingResult())
    val bonusesInfo: LiveData<SResult<BonusesInfo>> = _bonusesInfo

    fun updateBonuses() {
        safeLaunch {
            _bonusesInfo.postValue(loadingResult())
            _bonusesInfo.postValue(repo.getBonusesInfo())
        }
    }
}