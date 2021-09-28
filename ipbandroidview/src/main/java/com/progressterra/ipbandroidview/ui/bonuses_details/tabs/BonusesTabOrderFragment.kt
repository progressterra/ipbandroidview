package com.progressterra.ipbandroidview.ui.bonuses_details.tabs

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.models.Purchase
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentDetailBonusesOrderTabLibBinding
import com.progressterra.ipbandroidview.databinding.ItemOrderLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseBindingFragment
import com.progressterra.ipbandroidview.ui.bonuses_details.BonusesDetailsViewModel
import com.progressterra.ipbandroidview.utils.ui.adapters.RecyclerViewAdapter

internal class BonusesTabOrderFragment :
    BaseBindingFragment<FragmentDetailBonusesOrderTabLibBinding, BonusesDetailsViewModel>(R.layout.fragment_detail_bonuses_order_tab_lib) {

    override val vm by activityViewModels<BonusesDetailsViewModel>()

    private val adapter =
        RecyclerViewAdapter<Purchase>(
            R.layout.item_order_lib,
            onNormalBind = { binding, country ->
                (binding as ItemOrderLibBinding).apply {
                    lifecycleOwner = viewLifecycleOwner
                    item = country
                    binding.root.setOnClickListener {

                    }
                }
            })

    override fun onInitBinding(
        binding: FragmentDetailBonusesOrderTabLibBinding,
        savedInstanceState: Bundle?
    ) {
        super.onInitBinding(binding, savedInstanceState)
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        binding.orderListRv.adapter = adapter
    }

    private fun setupViewModel() {
        vm.purchasesList.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
    }

    override fun onResume() {
        super.onResume()
        view?.requestLayout()
    }
}