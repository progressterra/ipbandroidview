package com.progressterra.ipbandroidview.ui.bonuses_details

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentDetailBonusesLibBinding
import com.progressterra.ipbandroidview.ui.bonuses_details.tabs.*
import com.progressterra.ipbandroidview.utils.ScreenState


class BonusesDetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBonusesLibBinding
    private val viewModel: BonusesDetailsViewModel by activityViewModels()
    private var onPurchaseClickListener: OnPurchaseClickListener? = null


    private var mainTextColor: String? = null
    private var secondaryTextColor: String? = null
    private var mainColor: String? = null
    private var secondaryColor: String? = null
    private var positiveTextColor: String? = null
    private var negativeTextColor: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getValuesFromArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_detail_bonuses_lib,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        initListeners()
    }

    private fun getValuesFromArguments() {
        arguments?.getString(MAIN_TEXT_COLOR_ARG)?.let {
            ColorsPalette.mainTextColor = Color.parseColor(it)
        }
        arguments?.getString(SECONDARY_TEXT_COLOR_ARG)?.let {
            ColorsPalette.secondaryTextColor = Color.parseColor(it)
        }
        arguments?.getString(MAIN_COLOR_ARG)?.let {
            ColorsPalette.mainColor = Color.parseColor(it)
        }
        arguments?.getString(SECONDARY_COLOR_ARG)?.let {
            ColorsPalette.secondaryColor = Color.parseColor(it)
        }

        arguments?.getString(POSITIVE_COLOR_ARG)?.let {
            ColorsPalette.positiveTextColor = Color.parseColor(it)
        }
        arguments?.getString(NEGATIVE_COLOR_ARG)?.let {
            ColorsPalette.negativeTextColor = Color.parseColor(it)
        }
    }

    private fun setupViewModel() {

        viewModel.status.observe(viewLifecycleOwner) {
            binding.screenState = it
            if (it == ScreenState.ERROR) {
                Snackbar.make(binding.root, getString(R.string.ErrorString), Snackbar.LENGTH_SHORT)
                    .show()
            }
        }

        viewModel.bonusesInfo.observe(viewLifecycleOwner) {
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

    fun initListeners() {
        binding.refreshButton.setOnClickListener {
            viewModel.updateDetailBonusesInfo()
        }
    }

    inner class DemoCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> BonusesTabDetailFragment()
                1 -> BonusesTabTransactionFragment()
                2 -> BonusesTabOrderFragment().also {
                    it.onPurchaseClickListener = onPurchaseClickListener
                }
                else -> throw  IllegalStateException("Incorrect position")
            }
        }
    }

    companion object {
        const val MAIN_COLOR_ARG = "theme_color_arg"
        const val SECONDARY_COLOR_ARG = "secondary_color_arg"
        const val MAIN_TEXT_COLOR_ARG = "base_text_color"
        const val SECONDARY_TEXT_COLOR_ARG = "secondary_text_color"
        const val POSITIVE_COLOR_ARG = "positive_color"
        const val NEGATIVE_COLOR_ARG = "negavite_color"

        fun newInstance(
            onPurchaseClickListener: OnPurchaseClickListener? = null,
            mainColor: String? = null,
            secondaryColor: String? = null,
            mainTextColor: String? = null,
            secondaryTextColor: String? = null,
            negativeTextColor: String? = null,
            positiveTetColor: String? = null
        ): BonusesDetailFragment {
            return BonusesDetailFragment().also {
                val args = Bundle()
                it.onPurchaseClickListener = onPurchaseClickListener
                args.putString(MAIN_TEXT_COLOR_ARG, mainTextColor)
                args.putString(SECONDARY_TEXT_COLOR_ARG, secondaryTextColor)
                args.putString(MAIN_COLOR_ARG, mainColor)
                args.putString(SECONDARY_COLOR_ARG, secondaryColor)
                args.putString(NEGATIVE_COLOR_ARG, negativeTextColor)
                args.putString(POSITIVE_COLOR_ARG, positiveTetColor)
                it.arguments = args
            }
        }
    }

}