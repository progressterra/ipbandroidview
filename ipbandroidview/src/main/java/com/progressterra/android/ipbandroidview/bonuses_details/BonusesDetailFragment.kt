package com.progressterra.android.ipbandroidview.bonuses_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.progressterra.android.ipbandroidview.bonuses_details.tabs.BonusesTabDetailFragment
import com.progressterra.android.ipbandroidview.bonuses_details.tabs.BonusesTabOrderFragment
import com.progressterra.android.ipbandroidview.bonuses_details.tabs.BonusesTabTransactionFragment
import com.progressterra.android.ipbandroidview.utils.ScreenState
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentDetailBonusesBinding


class BonusesDetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBonusesBinding
    private lateinit var viewModel: BonusesDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_bonuses,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(BonusesDetailsViewModel::class.java)

        viewModel.updateDetailBonusesInfo()

        viewModel.status.observe(this) {
            binding.screenState = it
            if (it == ScreenState.ERROR) {
                Snackbar.make(binding.root, getString(R.string.ErrorString), Snackbar.LENGTH_SHORT)
                    .show()
            }
        }

        viewModel.bonusesInfo.observe(this) {
            binding.bonusesInfo = it
            setupTabAdapter()
        }
    }

    private fun setupTabAdapter() {
        val demoCollectionAdapter = DemoCollectionAdapter(this)

        binding.pager.adapter = demoCollectionAdapter

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.bonuses)
                1 -> tab.text = getString(R.string.transactions)
                2 -> tab.text = getString(R.string.my_orders)
            }
        }.attach()
    }

    fun refreshBonusesData() {
        viewModel.updateDetailBonusesInfo()
    }

    private class DemoCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> BonusesTabDetailFragment()
                1 -> BonusesTabTransactionFragment()
                2 -> BonusesTabOrderFragment()
                else -> throw  IllegalStateException("Incorrect position")
            }
        }
    }

    fun setSecondaryTextColor() {

    }

    fun setPrimaryTextColor() {

    }

    fun setThemeColor() {

    }

    fun setPositiveColor() {

    }

    fun setNegativeColor() {

    }
}