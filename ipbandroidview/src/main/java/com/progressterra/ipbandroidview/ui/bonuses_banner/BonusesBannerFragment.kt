package com.progressterra.ipbandroidview.ui.bonuses_banner

import androidx.fragment.app.viewModels
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentBonusesBannerLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseBindingFragment

class BonusesBannerFragment :
    BaseBindingFragment<FragmentBonusesBannerLibBinding, BonusesBannerViewModel>(
        R.layout.fragment_bonuses_banner_lib
    ) {
    override val vm by viewModels<BonusesBannerViewModel>()

    override fun onResume() {
        super.onResume()
        vm.updateBonuses()
    }
}