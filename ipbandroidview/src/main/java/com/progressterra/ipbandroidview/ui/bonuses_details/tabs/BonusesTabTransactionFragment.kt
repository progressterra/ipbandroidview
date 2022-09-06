package com.progressterra.ipbandroidview.ui.bonuses_details.tabs

import android.os.Bundle
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.data.model.Transaction
import com.progressterra.ipbandroidview.databinding.FragmentDetailBonusesTransactionTabLibBinding
import com.progressterra.ipbandroidview.databinding.ItemTransactionLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseBindingFragment
import com.progressterra.ipbandroidview.ui.bonuses_details.BonusesDetailsViewModel
import com.progressterra.ipbandroidview.utils.ui.adapters.RecyclerViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class BonusesTabTransactionFragment :
    BaseBindingFragment<FragmentDetailBonusesTransactionTabLibBinding, BonusesDetailsViewModel>(R.layout.fragment_detail_bonuses_transaction_tab_lib) {

    override val vm by viewModel<BonusesDetailsViewModel>()

    private val adapter =
        RecyclerViewAdapter<Transaction>(
            R.layout.item_transaction_lib,
            onNormalBind = { binding, transaction ->
                (binding as ItemTransactionLibBinding).apply {
                    lifecycleOwner = viewLifecycleOwner
                    item = transaction
                }
            })

    override fun onInitBinding(
        binding: FragmentDetailBonusesTransactionTabLibBinding,
        savedInstanceState: Bundle?
    ) {
        super.onInitBinding(binding, savedInstanceState)
        setupViewModel()
        setupView()
    }

    private fun setupView() {
        binding.bonusTransactionRv.adapter = adapter
    }

    private fun setupViewModel() {
        vm.transactionList.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
    }

    override fun onResume() {
        super.onResume()
        // перерасчет высоты контейнера фрагмента, так как используется viewPager с экрнами разной высоты
        view?.requestLayout()
    }
}