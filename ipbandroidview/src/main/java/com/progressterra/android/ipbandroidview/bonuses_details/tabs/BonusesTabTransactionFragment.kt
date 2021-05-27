package com.progressterra.android.ipbandroidview.bonuses_details.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.progressterra.android.ipbandroidview.bonuses_details.BonusesDetailsViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentDetailBonusesTransactionTabBinding

internal class BonusesTabTransactionFragment : Fragment() {
    private lateinit var viewModel: BonusesDetailsViewModel
    private lateinit var binding: FragmentDetailBonusesTransactionTabBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail_bonuses_transaction_tab,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel =
            ViewModelProvider(requireActivity()).get(BonusesDetailsViewModel::class.java)

        viewModel.transactionList.observe(this) {
            binding.bonusTransactionRv.adapter = TransactionAdapter(it.transactions)
        }
    }

    override fun onResume() {
        super.onResume()
        view?.requestLayout()
    }
}