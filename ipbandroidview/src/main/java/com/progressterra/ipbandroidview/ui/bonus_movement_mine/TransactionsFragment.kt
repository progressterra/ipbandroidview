package com.progressterra.ipbandroidview.ui.bonus_movement_mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.progressterra.ipbandroidview.databinding.FragmentTransactionsBinding
import com.progressterra.ipbandroidview.ui.base.BaseFragment
import com.progressterra.ipbandroidview.ui.bonus_movement_mine.adapter.TransactionsAdapter
import com.progressterra.ipbandroidview.utils.Event

class TransactionsFragment : BaseFragment() {

    override val vm by viewModels<TransactionsViewModel>()

    private lateinit var binding: FragmentTransactionsBinding

    private val adapter = TransactionsAdapter(emptyList())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = vm
        binding.lifecycleOwner = viewLifecycleOwner
        binding.rvTransactions.adapter = adapter
        vm.transactionList.observe(viewLifecycleOwner) {
            adapter.items = it
            adapter.notifyDataSetChanged()
        }
        binding.swipeRefresh.setOnRefreshListener { vm.getInfo(true) }
        vm.showError.observe(viewLifecycleOwner, this::showError)
    }

    private fun showError(errorEvent: Event<Int>) {
        errorEvent.contentIfNotHandled?.let {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
        }
    }

}