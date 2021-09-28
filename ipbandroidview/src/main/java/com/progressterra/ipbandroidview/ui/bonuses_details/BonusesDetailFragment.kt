package com.progressterra.ipbandroidview.ui.bonuses_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentDetailBonusesLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseBindingFragment
import com.progressterra.ipbandroidview.ui.bonuses_details.tabs.BonusesTabDetailFragment
import com.progressterra.ipbandroidview.ui.bonuses_details.tabs.BonusesTabOrderFragment
import com.progressterra.ipbandroidview.ui.bonuses_details.tabs.BonusesTabTransactionFragment


class BonusesDetailFragment :
    BaseBindingFragment<FragmentDetailBonusesLibBinding, BonusesDetailsViewModel>(R.layout.fragment_detail_bonuses_lib) {

    override val vm by activityViewModels<BonusesDetailsViewModel>()

    override fun onInitBinding(
        binding: FragmentDetailBonusesLibBinding,
        savedInstanceState: Bundle?
    ) {
        super.onInitBinding(binding, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        setupTabAdapter()
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

    inner class DemoCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
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

}