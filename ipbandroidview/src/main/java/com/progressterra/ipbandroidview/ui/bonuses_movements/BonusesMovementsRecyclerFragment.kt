package com.progressterra.ipbandroidview.ui.bonuses_movements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.progressterra.ipbandroidview.databinding.FragmentBonusesMovementsLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseFragment
import com.progressterra.ipbandroidview.ui.bonuses_movements.adapter.BonusesMovementsAdapter
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ScreenState
import org.koin.androidx.viewmodel.ext.android.viewModel

class BonusesMovementsRecyclerFragment : BaseFragment() {

    override val vm by viewModel<BonusesMovementsViewModel>()

    private lateinit var binding: FragmentBonusesMovementsLibBinding

    private val adapter = BonusesMovementsAdapter(emptyList())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBonusesMovementsLibBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupViewModel()
    }

    private fun setupViewModel() {
        vm.transactionList.observe(viewLifecycleOwner) {
            adapter.items = it
            adapter.notifyDataSetChanged()
        }
        vm.showError.observe(viewLifecycleOwner, this::showError)
        vm.screenState.observe(viewLifecycleOwner) {
            if (it != ScreenState.LOADING)
                binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun setupView() {
        binding.apply {
            vm = this@BonusesMovementsRecyclerFragment.vm
            lifecycleOwner = viewLifecycleOwner
            rvTransactions.adapter = adapter
            swipeRefresh.setOnRefreshListener {
                this@BonusesMovementsRecyclerFragment.vm.getInfo(true)
            }
        }
    }

    private fun showError(errorEvent: Event<Int>) {
        errorEvent.contentIfNotHandled?.let {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
        }
    }
}