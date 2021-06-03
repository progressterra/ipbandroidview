package com.progressterra.ipbandroidview.ui.bonuses_details.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentDetailBonusesMainTabBinding
import com.progressterra.ipbandroidview.ui.bonuses_details.BonusesDetailsViewModel

internal class BonusesTabDetailFragment : Fragment() {

    private val viewModel: BonusesDetailsViewModel by activityViewModels()
    private lateinit var binding: FragmentDetailBonusesMainTabBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail_bonuses_main_tab,
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
        viewModel.bonusMessageList.observe(viewLifecycleOwner) {
            binding.bonusMessagesRv.adapter = BonusMessageListAdapter(it)
        }

    }
}