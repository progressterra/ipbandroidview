package com.progressterra.ipbandroidview.ui.bonuses_details.tabs

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.models.BonusMessage
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentDetailBonusesMainTabLibBinding
import com.progressterra.ipbandroidview.databinding.ItemBonusMessageLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseBindingFragment
import com.progressterra.ipbandroidview.ui.bonuses_details.BonusesDetailsViewModel
import com.progressterra.ipbandroidview.ui.promocode.PromoCodeViewModel
import com.progressterra.ipbandroidview.utils.ui.adapters.RecyclerViewAdapter

internal class BonusesTabDetailFragment :
    BaseBindingFragment<FragmentDetailBonusesMainTabLibBinding, PromoCodeViewModel>(R.layout.fragment_detail_bonuses_main_tab_lib) {

    override val vm by viewModels<BonusesDetailsViewModel>(ownerProducer = { requireParentFragment() })

    private val adapter =
        RecyclerViewAdapter<BonusMessage>(
            R.layout.item_bonus_message_lib,
            onNormalBind = { binding, bonusMessage ->
                (binding as ItemBonusMessageLibBinding).apply {
                    lifecycleOwner = viewLifecycleOwner
                    item = bonusMessage
                }
            })

    override fun onInitBinding(
        binding: FragmentDetailBonusesMainTabLibBinding,
        savedInstanceState: Bundle?
    ) {
        super.onInitBinding(binding, savedInstanceState)
        setupViewModel()
        setupView()
    }

    private fun setupView() {
        binding.bonusMessagesRv.adapter = adapter
    }

    private fun setupViewModel() {
        vm.bonusMessageList.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
    }

    override fun onResume() {
        super.onResume()
        view?.requestLayout()
    }
}