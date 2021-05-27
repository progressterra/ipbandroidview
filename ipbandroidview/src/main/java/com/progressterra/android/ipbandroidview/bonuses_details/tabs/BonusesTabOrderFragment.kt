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
import com.progressterra.ipbandroidview.databinding.FragmentDetailBonusesOrderTabBinding

internal class BonusesTabOrderFragment : Fragment() {
    private lateinit var binding: FragmentDetailBonusesOrderTabBinding
    private lateinit var viewModel: BonusesDetailsViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail_bonuses_order_tab,
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
        viewModel.purchasesList.observe(this) {
            binding.orderListRv.adapter = OrderAdapter(it)
        }
    }
}