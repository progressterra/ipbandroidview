package com.progressterra.android.ipbandroidview.bonuses_details.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.progressterra.android.ipbandroidview.bonuses_details.BonusesDetailsViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentDetailBonusesMainTabBinding

class BonusesTabDetailFragment : Fragment() {
    private lateinit var bonusesDetailsViewModel: BonusesDetailsViewModel
    private lateinit var binding: FragmentDetailBonusesMainTabBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBonusesMainTabBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bonusesDetailsViewModel =
            ViewModelProvider(requireActivity()).get(BonusesDetailsViewModel::class.java)
        bonusesDetailsViewModel.bonusesInfo.observe(this){
            binding.bonusesInfo = it
        }
    }
}