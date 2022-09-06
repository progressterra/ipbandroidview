package com.progressterra.ipbandroidview.ui.bonuses_banner

import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentBonusesBannerLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class BonusesBannerFragment :
    BaseBindingFragment<FragmentBonusesBannerLibBinding, BonusesBannerViewModel>(
        R.layout.fragment_bonuses_banner_lib
    ) {
    override val vm by viewModel<BonusesBannerViewModel>()

    override fun onResume() {
        super.onResume()
        vm.updateBonuses()
    }
}